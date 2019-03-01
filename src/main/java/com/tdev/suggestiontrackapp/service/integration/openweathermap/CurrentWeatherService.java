package com.tdev.suggestiontrackapp.service.integration.openweathermap;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tdev.suggestiontrackapp.config.ConfigApp;
import com.tdev.suggestiontrackapp.config.ConstantsApp;
import com.tdev.suggestiontrackapp.model.currentWeather.CurrentWeatherResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrentWeatherService {
	
	
	@Autowired
	private ConfigApp configApp;
	
	public Optional<CurrentWeatherResponse> getCurrentWeather() {		
		log.info("GETTING CURRENT WEATHER");
		UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(configApp.getUrlCurrentWeather())
			    .queryParam(ConstantsApp.HEADER_APP_ID_OPEN_WEATHER_MAP, configApp.getAppIdOpenWeatherMap())
			    .queryParam("id", "2172797");
		
		RestTemplate restTemplate = new RestTemplate();
		CurrentWeatherResponse response = restTemplate
				  .getForObject(builder.toUriString(), CurrentWeatherResponse.class);
		
		log.info("CURRENT WEATHER SUCCESSFULLY OBTAINED");
		return Optional.of(response);
	}
}
