package com.torokdan.weatherapp.controller;

import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AppUserController {

  private final AppUserService userService;

  public AppUserController(AppUserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody AppUserRequestDto user) {
    return ResponseEntity.ok(userService.createUser(user));
  }
}
