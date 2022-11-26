package com.torokdan.weatherapp.security.configration;

import com.torokdan.weatherapp.security.filter.AuthenticationFilter;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class AuthenticationFilterConfigurer extends AbstractHttpConfigurer<AuthenticationFilterConfigurer, HttpSecurity> {

  @Override
  public void configure(HttpSecurity builder) throws Exception {
    AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
    builder.addFilter(new AuthenticationFilter(authenticationManager));
  }

  public static AuthenticationFilterConfigurer authenticationFilterConfigurer() {
    return new AuthenticationFilterConfigurer();
  }
}
