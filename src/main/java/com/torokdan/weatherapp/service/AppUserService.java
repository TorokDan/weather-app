package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;

public interface AppUserService {
  AppUserResponseDto createUser(AppUserRequestDto request);

}
