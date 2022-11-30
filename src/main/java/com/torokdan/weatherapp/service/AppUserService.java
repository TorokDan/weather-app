package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
import com.torokdan.weatherapp.model.dto.DeleteLocationFromUserResponseDto;
import com.torokdan.weatherapp.model.entity.AppUser;
import com.torokdan.weatherapp.model.entity.Location;
import java.util.List;

public interface AppUserService {
  AppUserResponseDto createUser(AppUserRequestDto request);
  List<AppUserResponseDto> listUsers();
  void addLocationToUser(String userName, Location location);
  AppUser findUser(String name);
  DeleteLocationFromUserResponseDto removeLocationFromUser(String userName, Location location);

}
