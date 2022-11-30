package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.exception.LocationAlreadyExistsException;
import com.torokdan.weatherapp.exception.LocationNotFoundException;
import com.torokdan.weatherapp.exception.LocationNotInTheListException;
import com.torokdan.weatherapp.exception.NoLocationFoundException;
import com.torokdan.weatherapp.exception.NoLocationFoundWithTheProvidedIdException;
import com.torokdan.weatherapp.model.dto.DeleteLocationFromUserResponseDto;
import com.torokdan.weatherapp.model.dto.LocationRequestDto;
import com.torokdan.weatherapp.model.entity.Location;
import com.torokdan.weatherapp.repository.LocationRepository;
import com.torokdan.weatherapp.service.AppUserService;
import com.torokdan.weatherapp.service.LocationService;
import com.torokdan.weatherapp.service.ModelCreator;
import java.util.List;
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
  public void createLocation(LocationRequestDto locationRequestDto) {
    if (locationRepository.existsByName(locationRequestDto.getName())) {
      throw new LocationAlreadyExistsException(locationRequestDto.getName());
    }
    locationRepository.save(modelCreator.createLocation(locationRequestDto));
  }

  @Override
  public void addLocationToUser(String userName, LocationRequestDto location) {
    Location loc = saveLocation(location);
    userService.addLocationToUser(userName, loc);
  }

  @Override
  public List<Location> listLocations() {
    List<Location> locations = locationRepository.findAll();
    if (locations.size() == 0) {
      throw new NoLocationFoundException();
    }
    return locations;
  }

  @Override
  public DeleteLocationFromUserResponseDto deleteLocationFromUser(String userName, String locationName) {
    Location location = locationRepository.findByName(locationName)
        .orElseThrow(() -> new LocationNotFoundException(locationName));
    return userService.removeLocationFromUser(userName, location);

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
