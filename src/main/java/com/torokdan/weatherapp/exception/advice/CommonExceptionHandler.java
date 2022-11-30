package com.torokdan.weatherapp.exception.advice;

import com.torokdan.weatherapp.exception.EmailAlreadyExistsException;
import com.torokdan.weatherapp.exception.LocationAlreadyAddedToTheUserException;
import com.torokdan.weatherapp.exception.LocationAlreadyExistsException;
import com.torokdan.weatherapp.exception.LocationNotFoundException;
import com.torokdan.weatherapp.exception.LocationNotInTheListException;
import com.torokdan.weatherapp.exception.NoLocationFoundException;
import com.torokdan.weatherapp.exception.NoLocationFoundWithTheProvidedIdException;
import com.torokdan.weatherapp.exception.NoLocationProvidedException;
import com.torokdan.weatherapp.exception.NoUserFoundException;
import com.torokdan.weatherapp.exception.RoleNotFoundException;
import com.torokdan.weatherapp.exception.UsernameAlreadyExistsException;
import com.torokdan.weatherapp.model.dto.ErrorResponseDto;
import org.apache.tomcat.jni.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

  @ExceptionHandler(value = {EmailAlreadyExistsException.class, UsernameAlreadyExistsException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleRegistrationErrors(RuntimeException exception) {
    return new ErrorResponseDto(exception.getMessage());
  }

  // it should show custom error message....
  @ExceptionHandler(value = {NoUserFoundException.class, NoLocationFoundException.class})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ErrorResponseDto handleListingUsers(RuntimeException exception) {
    return new ErrorResponseDto(exception.getMessage());
  }

  @ExceptionHandler(value = {LocationAlreadyAddedToTheUserException.class,
      LocationNotInTheListException.class,
      NoLocationProvidedException.class,
      LocationAlreadyExistsException.class,
      NoLocationFoundWithTheProvidedIdException.class,
      LocationNotFoundException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleLocationErrors(RuntimeException exception) {
    return new ErrorResponseDto(exception.getMessage());
  }

  @ExceptionHandler(value = RoleNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleRoleErrors(RuntimeException exception) {
    return new ErrorResponseDto(exception.getMessage());
  }
}
