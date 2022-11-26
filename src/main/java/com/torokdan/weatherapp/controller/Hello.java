package com.torokdan.weatherapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello {

  @Value("${config.security.secret}")
  private String secret;
  @GetMapping
  public ResponseEntity hello() {
    System.out.println(secret);
    return ResponseEntity.ok("Hello world!");
  }
}
