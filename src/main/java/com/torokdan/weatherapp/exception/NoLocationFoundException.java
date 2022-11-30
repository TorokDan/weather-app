package com.torokdan.weatherapp.exception;

public class NoLocationFoundException extends RuntimeException {

  public NoLocationFoundException() {
    super("No location found in the database");
  }
}
