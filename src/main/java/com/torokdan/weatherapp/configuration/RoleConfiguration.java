package com.torokdan.weatherapp.configuration;

import com.torokdan.weatherapp.model.entity.Role;
import com.torokdan.weatherapp.model.RoleType;
import com.torokdan.weatherapp.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfiguration implements CommandLineRunner {

  private final RoleService roleService;

  public RoleConfiguration(RoleService roleService) {
    this.roleService = roleService;
  }

  @Override
  public void run(String... args) throws Exception {
    RoleType[] roles = RoleType.values();
    for (RoleType role :
        roles) {
      if (!roleService.exists(role)) {
        roleService.save(new Role(role));
      }
    }
  }
}
