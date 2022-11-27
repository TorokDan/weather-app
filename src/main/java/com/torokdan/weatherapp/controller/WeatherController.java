package com.torokdan.weatherapp.controller;

import com.torokdan.weatherapp.model.json.Data;
import com.torokdan.weatherapp.model.Url;
import com.torokdan.weatherapp.service.ConnectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

  private ConnectionService connectionService;
  public WeatherController(ConnectionService connectionService) {
    this.connectionService = connectionService;
  }

  @GetMapping("/{location}")
  public ResponseEntity location(@PathVariable String location) {
    return ResponseEntity.ok().body(connectionService.gatherDataFromLocation(location));
  }

}
