package com.torokdan.weatherapp.service;

import com.torokdan.weatherapp.model.Role;
import com.torokdan.weatherapp.model.RoleType;
import java.util.Optional;

public interface RoleService {
  void save(Role role);
  boolean exists(RoleType role);
  Role findRole(RoleType role);

}
