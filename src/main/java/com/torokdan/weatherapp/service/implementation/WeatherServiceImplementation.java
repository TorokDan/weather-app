package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.configuration.property.WeatherAppProperties;
import com.torokdan.weatherapp.exception.NoLocationProvidedException;
import com.torokdan.weatherapp.model.Url;
import com.torokdan.weatherapp.model.dto.DataResponseDto;
import com.torokdan.weatherapp.model.dto.WeatherListResponseDto;
import com.torokdan.weatherapp.model.entity.AppUser;
import com.torokdan.weatherapp.model.entity.Location;
import com.torokdan.weatherapp.service.AppUserService;
import com.torokdan.weatherapp.service.ConnectionService;
import com.torokdan.weatherapp.service.LocationService;
import com.torokdan.weatherapp.service.ModelCreator;
import com.torokdan.weatherapp.service.WeatherService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImplementation implements WeatherService {

  private final WeatherAppProperties weatherAppProperties;
  private final AppUserService userService;
  private final LocationService locationService;
  private final ConnectionService connectionService;
  private final ModelCreator modelCreator;

  public WeatherServiceImplementation(WeatherAppProperties weatherAppProperties,
      AppUserService userService,
      LocationService locationService,
      ConnectionService connectionService, ModelCreator modelCreator) {
    this.weatherAppProperties = weatherAppProperties;
    this.userService = userService;
    this.locationService = locationService;
    this.connectionService = connectionService;
    this.modelCreator = modelCreator;
  }


  @Override
  public WeatherListResponseDto listLocationsForUser(String userName) {
    AppUser user = userService.findUser(userName);
    List<Location> locations = user.getLocations();
    if (locations.size() == 0) {
      throw new NoLocationProvidedException();
    }
    List<DataResponseDto> data = new ArrayList<>();
    for (int i = 0; i < locations.size(); i++) {
      data.add(connectionService.gatherDataFromLocation(locations.get(i).getName()));
    }
    System.out.println(locations.get(0).getName());
    return modelCreator.createWeatherListResponseDto(user, data);
  }
}
