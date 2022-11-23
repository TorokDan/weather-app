package com.torokdan.weatherapp.model.dto;

import com.torokdan.weatherapp.model.AppUser;
import com.torokdan.weatherapp.model.Role;

public class AppUserResponseDto {
  private long id;
  private String username;
  private String email;
  private Role role;

  public AppUserResponseDto() {
  }

  public AppUserResponseDto(long id, String username, String email, Role role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.role = role;
  }

  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public Role getRole() {
    return role;
  }

  public String getEmail() {
    return email;
  }
}
