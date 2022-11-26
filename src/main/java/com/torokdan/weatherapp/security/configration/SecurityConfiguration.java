package com.torokdan.weatherapp.security.configration;

import com.torokdan.weatherapp.configuration.SecurityProperties;
import com.torokdan.weatherapp.security.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Value("${config.security.secret}")
  public final static String SECRET = "asd";

  private final UserDetailsService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final SecurityProperties securityProperties;

  public SecurityConfiguration(UserDetailsService userService,
      BCryptPasswordEncoder bCryptPasswordEncoder, SecurityProperties securityProperties) {
    this.userService = userService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.securityProperties = securityProperties;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
        AuthenticationManagerBuilder.class);
    authenticationManagerBuilder
        .userDetailsService(userService)
        .passwordEncoder(bCryptPasswordEncoder);

    http
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests().antMatchers(HttpMethod.GET, "/hello").hasAuthority("USER")
        .and()
        .authorizeRequests().antMatchers(HttpMethod.GET, "/**").permitAll()
        .and()
        .authorizeRequests().antMatchers(HttpMethod.POST, "/**").permitAll();

    http.apply(AuthenticationFilterConfigurer.authenticationFilterConfigurer(securityProperties));
    http.addFilterBefore(new AuthorizationFilter(securityProperties), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

}
