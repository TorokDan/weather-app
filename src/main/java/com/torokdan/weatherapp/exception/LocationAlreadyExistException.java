package com.torokdan.weatherapp.exception;

public class LocationAlreadyExistException extends
    RuntimeException {

  public LocationAlreadyExistException(String lcoation) {
    super("Location already exist: " + lcoation);
  }
}
