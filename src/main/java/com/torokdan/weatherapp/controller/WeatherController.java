package com.torokdan.weatherapp.controller;

import com.torokdan.weatherapp.service.ConnectionService;
import com.torokdan.weatherapp.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

  private final ConnectionService connectionService;
  private final WeatherService weatherService;
  public WeatherController(ConnectionService connectionService, WeatherService weatherService) {
    this.connectionService = connectionService;
    this.weatherService = weatherService;
  }

  @GetMapping("location/{location}")
  public ResponseEntity location(@PathVariable String location) {
    return ResponseEntity.ok().body(connectionService.gatherDataFromLocation(location));
  }

  @GetMapping("/user/{userName}")
  public ResponseEntity listLocationsForUser(@PathVariable String userName) {
//    return ResponseEntity.ok("asd");
    return ResponseEntity.ok().body(
        weatherService.listLocationsForUser(userName)
    );
  }

}
