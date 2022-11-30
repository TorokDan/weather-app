package com.torokdan.weatherapp.security.filter;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.torokdan.weatherapp.configuration.property.SecurityProperties;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthorizationFilter extends OncePerRequestFilter {

  private final SecurityProperties securityProperties;

  public AuthorizationFilter(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain) throws ServletException, IOException {
    if (request.getServletPath().equals("/login")) {
      filterChain.doFilter(request, response);
    } else {
      String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
      if (authorizationHeader != null && authorizationHeader.startsWith(securityProperties.bearer())) {
        try {
          String token = authorizationHeader.replace("\"", "").substring(securityProperties.bearer().length()+1);
          Algorithm algorithm = Algorithm.HMAC256(securityProperties.secret().getBytes());
          JWTVerifier verifier = JWT.require(algorithm).build();
          DecodedJWT decodedJWT = verifier.verify(token);
          String username = decodedJWT.getSubject();
          String role = decodedJWT.getClaim("role").asArray(String.class)[0];
          Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
          authorities.add(new SimpleGrantedAuthority(role));
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
              username, null, authorities);
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          filterChain.doFilter(request, response);
        } catch (Exception exception) {
          response.setHeader("error", exception.getMessage());
          response.setStatus(FORBIDDEN.value());
          Map<String, String> error = new HashMap<>();
          error.put("error_message", exception.getMessage());
          response.setContentType(APPLICATION_JSON_VALUE);
          new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
      } else {
        filterChain.doFilter(request, response);
      }
    }
  }
}
