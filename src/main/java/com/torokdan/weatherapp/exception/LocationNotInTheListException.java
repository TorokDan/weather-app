package com.torokdan.weatherapp.exception;

public class LocationNotInTheListException extends RuntimeException {

  public LocationNotInTheListException(String location) {
    super("location not exist: " + location);
  }
}
