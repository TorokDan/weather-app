package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.entity.Role;
import com.torokdan.weatherapp.model.RoleType;

public interface RoleService {
  void save(Role role);
  boolean exists(RoleType role);
  Role findRole(RoleType role);

}
