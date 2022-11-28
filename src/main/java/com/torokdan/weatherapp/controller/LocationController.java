package com.torokdan.weatherapp.controller;

import com.torokdan.weatherapp.model.dto.LocationRequestDto;
import com.torokdan.weatherapp.service.AppUserService;
import com.torokdan.weatherapp.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping("/{userName}")
  public void addLocation(@PathVariable String userName, @RequestBody LocationRequestDto location) {
  }


}
