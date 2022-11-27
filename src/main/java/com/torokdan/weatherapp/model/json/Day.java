package com.torokdan.weatherapp.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {
  private String datetime;
  private double temp;

  public String getDatetime() {
    return datetime;
  }

  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }

  public double getTemp() {
    return temp;
  }

  public void setTemp(double temp) {
    this.temp = temp;
  }

  public double getCelsius() {
    return Math.round((temp - 32) / 1.8);
  }

  @Override
  public String toString() {
    return datetime + ": " + temp;
  }
}
