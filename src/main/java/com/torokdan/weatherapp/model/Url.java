package com.torokdan.weatherapp.model;

import com.torokdan.weatherapp.configuration.property.WeatherAppProperties;

public class Url {

  private String url;
  private String apiKey;
  private String location;


  public Url(WeatherAppProperties weatherAppProperties, String location) {
    this.url = weatherAppProperties.url();
    this.apiKey = weatherAppProperties.apikey();
    this.location = location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return location;
  }

  @Override
  public String toString() {
    return String.format("%s/%s/%s/?key=%s", this.url, this.location, java.time.LocalDate.now(), this.apiKey);
  }
}
