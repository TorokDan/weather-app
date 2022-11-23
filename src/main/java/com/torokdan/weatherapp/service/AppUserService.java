package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
import java.util.List;

public interface AppUserService {
  AppUserResponseDto createUser(AppUserRequestDto request);
  List<AppUserResponseDto> listUsers();

}
