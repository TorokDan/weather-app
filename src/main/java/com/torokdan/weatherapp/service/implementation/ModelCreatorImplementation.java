package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.model.entity.AppUser;
import com.torokdan.weatherapp.model.RoleType;
import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
import com.torokdan.weatherapp.service.ModelCreator;
import com.torokdan.weatherapp.service.RoleService;
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
}
