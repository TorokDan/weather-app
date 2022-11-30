package com.torokdan.weatherapp.exception;

public class LocationNotFoundException extends RuntimeException {

  public LocationNotFoundException(String locationName) {
    super("Location not found: " + locationName);
  }
}
