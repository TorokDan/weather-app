package com.torokdan.weatherapp.security.filter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torokdan.weatherapp.configuration.property.SecurityProperties;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final SecurityProperties securityProperties;
  private final AuthenticationManager authenticationManager;

  private final int tokenDuration;
  private final String secret;

  public AuthenticationFilter(SecurityProperties securityProperties, AuthenticationManager authenticationManager) {
    this.securityProperties = securityProperties;
    this.authenticationManager = authenticationManager;
    tokenDuration = securityProperties.minutes() * 59 * 1000;
    secret = securityProperties.secret();
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    System.out.println(username + ": " + password);
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    return authenticationManager.authenticate(authenticationToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException, ServletException {
    System.out.println(securityProperties.minutes() + "::" + secret);
    User user = (User)authResult.getPrincipal();
    String accessToken = JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis()+tokenDuration))
        .withIssuer(request.getRequestURL().toString())
        .withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
        .sign(Algorithm.HMAC256(secret.getBytes()));
    response.setContentType(APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(), accessToken);
  }
}
