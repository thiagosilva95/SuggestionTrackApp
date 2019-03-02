package com.tdev.suggestiontrackapp.service.integration.openweathermap;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tdev.suggestiontrackapp.config.ConfigApp;
import com.tdev.suggestiontrackapp.config.AppConstants;
import com.tdev.suggestiontrackapp.model.currentWeather.CurrentWeatherResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrentWeatherService {
	
	@Autowired
	private ConfigApp configApp;
	
	public Optional<CurrentWeatherResponse> getCurrentWeatherByCityId() {		
		log.info("GETTING CURRENT WEATHER");
		UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(configApp.getUrlCurrentWeather())
			    .queryParam(AppConstants.HEADER_APP_ID_OPEN_WEATHER_MAP, configApp.getAppIdOpenWeatherMap())
			    .queryParam("id", "2172797");
		
		RestTemplate restTemplate = new RestTemplate();
		CurrentWeatherResponse response = restTemplate
				  .getForObject(builder.toUriString(), CurrentWeatherResponse.class);
		
		log.info("CURRENT WEATHER SUCCESSFULLY OBTAINED");
		return Optional.of(response);
	}
	
	public Optional<CurrentWeatherResponse> getCurrentWeatherByCitysName(String name) {		
		log.info("GETTING CURRENT WEATHER");
		List<String> params = Arrays.asList(new String[] {name, "br"});
		UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(configApp.getUrlCurrentWeather())
			    .queryParam(AppConstants.HEADER_APP_ID_OPEN_WEATHER_MAP, configApp.getAppIdOpenWeatherMap())
			    .queryParam("q", String.join(",", params));
		
		RestTemplate restTemplate = new RestTemplate();
		CurrentWeatherResponse response = restTemplate
				  .getForObject(builder.toUriString(), CurrentWeatherResponse.class);
		
		log.info("CURRENT WEATHER SUCCESSFULLY OBTAINED");
		return Optional.of(response);
	}
}
