package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.model.dto.DataResponseDto;
import com.torokdan.weatherapp.model.dto.DeleteLocationFromUserResponseDto;
import com.torokdan.weatherapp.model.dto.LocationRequestDto;
import com.torokdan.weatherapp.model.dto.WeatherListResponseDto;
import com.torokdan.weatherapp.model.entity.AppUser;
import com.torokdan.weatherapp.model.RoleType;
import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
import com.torokdan.weatherapp.model.entity.Location;
import com.torokdan.weatherapp.model.json.Data;
import com.torokdan.weatherapp.service.ModelCreator;
import com.torokdan.weatherapp.service.RoleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ModelCreatorImplementation implements ModelCreator {

  private final RoleService roleService;

  public ModelCreatorImplementation(RoleService roleService) {
    this.roleService = roleService;
  }

  @Override
  public AppUser createAppUser(AppUserRequestDto requestDto) {
    return new AppUser(requestDto.getUsername(),
        requestDto.getEmail(),
        requestDto.getPassword(),
        roleService.findRole(
            RoleType.USER));
  }

  @Override
  public AppUserResponseDto createAppUserResponseDto(AppUser user) {
    return new AppUserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
  }

  @Override
  public DataResponseDto createDataResponseDto(Data data) {
    return new DataResponseDto(data.getAddress(), data.getDays()[0].getDatetime(), data.getDays()[0].getTemp(),
        data.getDays()[0].getCelsius());
  }

  @Override
  public Location createLocation(LocationRequestDto locationRequestDto) {
    return new Location(locationRequestDto.getName());
  }

  @Override
  public WeatherListResponseDto createWeatherListResponseDto(AppUser user,
      List<DataResponseDto> data) {
    return new WeatherListResponseDto(createAppUserResponseDto(user), data);
  }

  @Override
  public DeleteLocationFromUserResponseDto createDeleteLocationFromUserResponseDto(AppUser user,
      Location location) {
    return new DeleteLocationFromUserResponseDto(user, location);
  }
}
