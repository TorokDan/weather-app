package com.torokdan.weatherapp.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
  private String address;
  private Day[] days;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Day[] getDays() {
    return days;
  }

  public void setDays(Day[] days) {
    this.days = days;
  }



  @Override
  public String toString() {
    return String.format("Today (%s), in %s there is %s Fahrenheit, which is %s Celsius.", days[0].getDatetime(), address, Math.round(days[0].getTemp()), Math.round((days[0].getTemp() - 32) / 1.8));
  }
}
