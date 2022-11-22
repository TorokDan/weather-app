package com.torokdan.weatherapp.exception.handler;

import com.torokdan.weatherapp.exception.EmailAlreadyExistsException;
import com.torokdan.weatherapp.exception.UsernameAlreadyExistsException;
import com.torokdan.weatherapp.model.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExcaptionHandler {

  @ExceptionHandler(value = {EmailAlreadyExistsException.class, UsernameAlreadyExistsException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseDto handleRegistrationErrors(RuntimeException exception) {
    return new ErrorResponseDto(exception.getMessage());
  }
}
