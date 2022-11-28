package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.dto.DataResponseDto;
import com.torokdan.weatherapp.model.dto.LocationRequestDto;
import com.torokdan.weatherapp.model.entity.AppUser;
import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
import com.torokdan.weatherapp.model.entity.Location;
import com.torokdan.weatherapp.model.json.Data;

public interface ModelCreator {
  AppUser createAppUser(AppUserRequestDto requestDto);
  AppUserResponseDto createAppUserResponseDto(AppUser user);
  DataResponseDto createDataResponseDto(Data data);
  Location createLocation(LocationRequestDto locationRequestDto);
}
