package com.torokdan.weatherapp.exception;

public class NoLocationFoundWithTheProvidedIdException extends
    RuntimeException {

  public NoLocationFoundWithTheProvidedIdException(Long locationId) {
    super("No Location found with the provided id: " + locationId);
  }
}
