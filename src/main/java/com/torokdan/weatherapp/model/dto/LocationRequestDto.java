package com.torokdan.weatherapp.model.dto;

public class LocationRequestDto {

  private String name;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocationRequestDto() {
  }

  public LocationRequestDto(String name) {
    this.name = name;
  }
}
