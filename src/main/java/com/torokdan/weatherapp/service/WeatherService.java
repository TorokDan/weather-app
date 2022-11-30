package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.dto.WeatherListResponseDto;

public interface WeatherService {
  WeatherListResponseDto listLocationsForUser(String userName);
}
