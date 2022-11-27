package com.torokdan.weatherapp.security.configration;

import com.torokdan.weatherapp.configuration.property.SecurityProperties;
import com.torokdan.weatherapp.security.filter.AuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class AuthenticationFilterConfigurer extends AbstractHttpConfigurer<AuthenticationFilterConfigurer, HttpSecurity> {

  private final SecurityProperties securityProperties;

  public AuthenticationFilterConfigurer(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  public void configure(HttpSecurity builder) throws Exception {
    AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
    builder.addFilter(new AuthenticationFilter(securityProperties, authenticationManager));
  }

  public static AuthenticationFilterConfigurer authenticationFilterConfigurer(SecurityProperties properties) {
    return new AuthenticationFilterConfigurer(properties);
  }
}
