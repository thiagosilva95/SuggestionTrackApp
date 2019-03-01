package com.tdev.suggestiontrackapp.service.integration.openweathermap;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tdev.suggestiontrackapp.model.CurrentWeatherResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrentWeatherService {
	
	private String url = "http://api.openweathermap.org/data/2.5/weather";

	public Optional<CurrentWeatherResponse> getCurrentWeather() {		
		UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(url)
			    .queryParam("APPID", "3672442c15e5eff710b85f9d09e8616e")
			    .queryParam("id", "2172797");
		
		RestTemplate restTemplate = new RestTemplate();
		CurrentWeatherResponse response = restTemplate
				  .getForObject(builder.toUriString(), CurrentWeatherResponse.class);
		
		return Optional.of(response);
	}
}
