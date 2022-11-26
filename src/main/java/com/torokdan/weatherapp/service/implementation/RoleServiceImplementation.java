package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.exception.RoleNotFoundException;
import com.torokdan.weatherapp.model.entity.Role;
import com.torokdan.weatherapp.model.RoleType;
import com.torokdan.weatherapp.repository.RoleRepository;
import com.torokdan.weatherapp.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImplementation implements RoleService {

  private final RoleRepository roleRepository;

  public RoleServiceImplementation(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public void save(Role role) {
    roleRepository.save(role);
  }

  @Override
  public boolean exists(RoleType role) {
    return roleRepository.existsByName(role.name());
  }

  @Override
  public Role findRole(RoleType role) {
    return roleRepository.findByName(role.name()).orElseThrow(() -> new RoleNotFoundException(role.name()));
  }
}
