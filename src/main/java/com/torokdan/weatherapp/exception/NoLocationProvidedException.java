package com.torokdan.weatherapp.exception;

public class NoLocationProvidedException extends
    RuntimeException {

  public NoLocationProvidedException() {
    super("There is no location to work with!");
  }
}
