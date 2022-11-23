package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.AppUser;
import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
import org.springframework.stereotype.Service;

public interface ModelCreator {
  AppUser createAppUser(AppUserRequestDto requestDto);
  AppUserResponseDto createAppUserResponseDto(AppUser user);
}
