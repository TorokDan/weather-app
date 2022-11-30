package com.torokdan.weatherapp.model.dto;

import com.torokdan.weatherapp.model.entity.AppUser;
import com.torokdan.weatherapp.model.entity.Location;

public class DeleteLocationFromUserResponseDto {
  private AppUser user;
  private Location deletedLocation;

  public DeleteLocationFromUserResponseDto(AppUser user, Location location) {
    this.user = user;
    this.deletedLocation = location;
  }

  public AppUser getUser() {
    return user;
  }

  public Location getDeletedLocation() {
    return deletedLocation;
  }
}
