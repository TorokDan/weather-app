package com.torokdan.weatherapp.model;

import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.service.RoleService;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String email;
  private String password;

  @ManyToOne
  private Role role;

  public AppUser() {
  }

  public AppUser(String username, String email, String password, Role role) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Role getRole() {
    return new Role(RoleType.USER);
  }

}
