package com.torokdan.weatherapp.controller;

import com.torokdan.weatherapp.exception.NoLocationProvidedException;
import com.torokdan.weatherapp.model.dto.LocationRequestDto;
import com.torokdan.weatherapp.service.AppUserService;
import com.torokdan.weatherapp.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edit")
public class UserLocationController {

  private final LocationService locationService;
  private final AppUserService userService;

  public UserLocationController(LocationService locationService, AppUserService userService) {
    this.locationService = locationService;
    this.userService = userService;
  }

  @PostMapping("/{userName}")
  public void addLocationToUser(@PathVariable String userName, @RequestBody LocationRequestDto location) {
    if (location == null || location.getName() == null) {
      throw new NoLocationProvidedException();
    }
    System.out.println(location.getName());
    locationService.addLocationToUser(userName, location);
  }

  @DeleteMapping("/{username}/{locationname}")
  public ResponseEntity deleteLocationFromUser(@PathVariable String username, @PathVariable String locationname) {
    return ResponseEntity.ok().body(locationService.deleteLocationFromUser(username, locationname));
  }
}
