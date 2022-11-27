package com.torokdan.weatherapp.model.dto;

public class DataResponseDto {
  private String address;
  private String dateTime;
  private double fahrenheit;
  private double celsius;

  public DataResponseDto(String address, String dateTime, double temp, double celsius) {
    this.address = address;
    this.dateTime = dateTime;
    this.fahrenheit = temp;
    this.celsius = celsius;
  }

  public String getAddress() {
    return address;
  }

  public String getDateTime() {
    return dateTime;
  }

  public double getFahrenheit() {
    return fahrenheit;
  }

  public double getCelsius() {
    return celsius;
  }
}
