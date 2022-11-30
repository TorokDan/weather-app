package com.torokdan.weatherapp.exception;

public class LocationAlreadyExistsException extends
    RuntimeException {

  public LocationAlreadyExistsException(String location) {
    super("Location already exists: " + location);
  }
}
