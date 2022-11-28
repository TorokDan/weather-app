package com.torokdan.weatherapp.exception.handler;

import com.torokdan.weatherapp.exception.EmailAlreadyExistsException;
import com.torokdan.weatherapp.exception.NoUserFoundException;
import com.torokdan.weatherapp.exception.UsernameAlreadyExistsException;
import com.torokdan.weatherapp.model.dto.ErrorResponseDto;
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
  @ExceptionHandler(value = NoUserFoundException.class)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ErrorResponseDto handleListingUsers(RuntimeException exception) {
    return new ErrorResponseDto(exception.getMessage());
  }
}
