package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.dto.LocationRequestDto;

public interface LocationService {

  void addLocationToUser(String userName, LocationRequestDto location);
}
