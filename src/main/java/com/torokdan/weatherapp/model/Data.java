package com.torokdan.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Arrays;

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
    return "Data{" +
        "address='" + address + '\'' +
        ", days=" + days[0].toString() +
        '}';
  }
}
