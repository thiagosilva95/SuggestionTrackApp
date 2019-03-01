package com.tdev.suggestiontrackapp.service.integration.spotify;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tdev.suggestiontrackapp.model.TokenSpotifyApiResponse;
import com.tdev.suggestiontrackapp.model.recommendation.Generes;
import com.tdev.suggestiontrackapp.model.recommendation.RecommendationResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpotifyApiService {

	private String urlAccesToken = "https://accounts.spotify.com/api/token";
	private String urlGenres = "https://api.spotify.com/v1/recommendations/available-genre-seeds";
	private String urlRecommendations = "https://api.spotify.com/v1/recommendations";
	private String clientId = "ae583995a2e149fdb43cdc2dc6c4d1c8";
	private String clientSecret = "4e4b71ce14bd4dc780e018f4539697eb";
	
	public TokenSpotifyApiResponse getAccessToken() {
		log.info("GETTING THE ACCES TOKEN");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(clientId, clientSecret);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("grant_type", "client_credentials");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		TokenSpotifyApiResponse response = restTemplate.postForObject(urlAccesToken, request, TokenSpotifyApiResponse.class);
		
		
		log.info("ACCES TOKEN SUCCESSFULLY OBTAINED");
		return response;
	}
	
	public Generes getAvailableGenreSeeds() {
		log.info("GETTING THE GENDER AVAILABLE");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(getAccessToken().getAccesToken());
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
		
		ResponseEntity<Generes> result = restTemplate.exchange(urlGenres, HttpMethod.GET, request, Generes.class);
		
		log.info("GENDERS GONE WITH SUCCESS");
		return result.getBody();
	}
	
	public RecommendationResponse getRecommendationsByGenere(String genere) {
		log.info("LOOKING FOR RECOMMENDATIONS OF TRACKS BY MUSICAL GENDER");
		RestTemplate restTemplate = new RestTemplate();
		
		UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(urlRecommendations)
			    .queryParam("market", "BR")
			    .queryParam("seed_genres", genere);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(getAccessToken().getAccesToken());
		
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		RecommendationResponse response = restTemplate.postForObject(builder.toUriString(), request, RecommendationResponse.class);
		
		log.info("TRACKS RECOMMENDATIONS GETTING SUCCESSFULLY");
		return response;
	}
}
