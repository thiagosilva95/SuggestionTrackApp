package com.tdev.suggestiontrackapp.model.currentWeather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainDTO {

	private Double temp;
	private Integer pressure;
	private Integer humidity;
	private Double temp_min;
	private Double temp_max;
	
}
