package com.torokdan.weatherapp.model.dto;

import com.torokdan.weatherapp.model.AppUser;

public class AppUserResponseDto {
  private long id;
  private String username;
  private String email;

  public AppUserResponseDto() {
  }

  public AppUserResponseDto(long id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }

  public static AppUserResponseDto from(AppUser user) {
    return new AppUserResponseDto(user.getId(), user.getUsername(), user.getEmail());
  }
}
