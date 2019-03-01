package com.tdev.suggestiontrackapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdev.suggestiontrackapp.model.CurrentWeatherResponse;
import com.tdev.suggestiontrackapp.service.integration.openweathermap.CurrentWeatherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("api/suggestion")
@RestController
public class SuggestionTrackController {
	
	@Autowired
	private CurrentWeatherService service;
	
	@GetMapping("/{city}")
	public ResponseEntity<CurrentWeatherResponse> getCurrentWeatherTest(@PathVariable String city) {
		Optional<CurrentWeatherResponse> response = service.getCurrentWeather();
		
		return response.isPresent() ? ResponseEntity.ok(response.get()) : ResponseEntity.notFound().build();
	}
}
