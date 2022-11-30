package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.dto.DeleteLocationFromUserResponseDto;
import com.torokdan.weatherapp.model.dto.LocationRequestDto;
import com.torokdan.weatherapp.model.entity.Location;
import java.util.List;

public interface LocationService {
  void createLocation(LocationRequestDto locationRequestDto);
  void addLocationToUser(String userName, LocationRequestDto location);
  List<Location> listLocations();
  DeleteLocationFromUserResponseDto deleteLocationFromUser(String userName, String locationName);
}
