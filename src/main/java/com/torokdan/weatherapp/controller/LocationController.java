package com.torokdan.weatherapp.controller;

import com.torokdan.weatherapp.model.dto.LocationRequestDto;
import com.torokdan.weatherapp.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

  private final LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping("/all")
  public ResponseEntity list() {
    return ResponseEntity.ok().body(locationService.listLocations());
  }

  @PostMapping()
  public void add(@RequestBody LocationRequestDto location) {
    locationService.createLocation(location);
  }
}
