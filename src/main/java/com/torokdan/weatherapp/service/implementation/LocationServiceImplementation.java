package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.model.dto.LocationRequestDto;
import com.torokdan.weatherapp.model.entity.Location;
import com.torokdan.weatherapp.repository.LocationRepository;
import com.torokdan.weatherapp.service.AppUserService;
import com.torokdan.weatherapp.service.LocationService;
import com.torokdan.weatherapp.service.ModelCreator;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImplementation implements LocationService {

  private final AppUserService userService;
  private final LocationRepository locationRepository;
  private final ModelCreator modelCreator;

  public LocationServiceImplementation(AppUserService userService,
      LocationRepository locationRepository, ModelCreator modelCreator) {
    this.userService = userService;
    this.locationRepository = locationRepository;
    this.modelCreator = modelCreator;
  }

  @Override
  public void addLocationToUser(String userName, LocationRequestDto location) {
    Location loc = saveLocation(location);

  }

  private Location saveLocation(LocationRequestDto location) {
    return locationRepository.findByName(location.getName())
        .orElseGet(() -> {
          Location tmp = modelCreator.createLocation(location);
          locationRepository.save(tmp);
          return tmp;
        });
  }
}
