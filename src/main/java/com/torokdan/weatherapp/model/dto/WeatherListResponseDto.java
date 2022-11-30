package com.torokdan.weatherapp.model.dto;

import java.util.List;

public class WeatherListResponseDto {
  private AppUserResponseDto user;
  private List<DataResponseDto> data;

  public WeatherListResponseDto(AppUserResponseDto user, List<DataResponseDto> data) {
    this.user = user;
    this.data = data;
  }

  public AppUserResponseDto getUser() {
    return user;
  }

  public List<DataResponseDto> getData() {
    return data;
  }
}
