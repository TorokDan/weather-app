package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.dto.DataResponseDto;
import com.torokdan.weatherapp.model.dto.DeleteLocationFromUserResponseDto;
import com.torokdan.weatherapp.model.dto.LocationRequestDto;
import com.torokdan.weatherapp.model.dto.WeatherListResponseDto;
import com.torokdan.weatherapp.model.entity.AppUser;
import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
import com.torokdan.weatherapp.model.entity.Location;
import com.torokdan.weatherapp.model.json.Data;
import java.util.List;

public interface ModelCreator {
  AppUser createAppUser(AppUserRequestDto requestDto);
  AppUserResponseDto createAppUserResponseDto(AppUser user);
  DataResponseDto createDataResponseDto(Data data);
  Location createLocation(LocationRequestDto locationRequestDto);
  WeatherListResponseDto createWeatherListResponseDto(AppUser user, List<DataResponseDto> data);
  DeleteLocationFromUserResponseDto createDeleteLocationFromUserResponseDto(AppUser user, Location location);
}
