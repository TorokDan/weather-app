package com.torokdan.weatherapp.exception;

public class LocationAlreadyAddedToTheUserException extends
    RuntimeException {

  public LocationAlreadyAddedToTheUserException(String lcoation) {
    super("Location already exist: " + lcoation);
  }
}
