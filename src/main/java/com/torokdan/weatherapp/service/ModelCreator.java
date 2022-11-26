package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.entity.AppUser;
import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;

public interface ModelCreator {
  AppUser createAppUser(AppUserRequestDto requestDto);
  AppUserResponseDto createAppUserResponseDto(AppUser user);
}
