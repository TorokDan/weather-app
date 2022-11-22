package com.torokdan.weatherapp.model;

import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String email;
  private String password;

  public AppUser() {
  }

  public AppUser(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public static AppUser from(AppUserRequestDto requestDto) {
    return new AppUser(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());
  }
}
