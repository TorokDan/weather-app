package com.torokdan.weatherapp.exception;

public class LocationNotExistException extends RuntimeException {

  public LocationNotExistException(String location) {
    super("location not exist: " + location);
  }
}
