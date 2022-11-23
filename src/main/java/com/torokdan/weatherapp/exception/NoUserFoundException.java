package com.torokdan.weatherapp.exception;

public class NoUserFoundException extends RuntimeException{

  public NoUserFoundException() {
    super("No user found in the database");
  }
}
