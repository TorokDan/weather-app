package com.torokdan.weatherapp.model.entity;

import com.torokdan.weatherapp.exception.LocationAlreadyExistException;
import com.torokdan.weatherapp.exception.LocationNotExistException;
import com.torokdan.weatherapp.model.RoleType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

  @OneToMany
  private List<Location> locations;

  public AppUser() {
  }

  public AppUser(String username, String email, String password, Role role) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
    this.locations = new ArrayList<>();
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

  public List<Location> getLocations() {
    return locations;
  }

  public void addLocation(String location) {
    Location loc = new Location(location);
    if (locations.contains(loc)) {
      throw new LocationAlreadyExistException(loc.getName());
    }
    locations.add(loc);
  }

  public void removeLocation(String location) {
    Location loc = new Location(location);
    if (locations.contains(loc)) {
      throw new LocationNotExistException(loc.getName());
    }
    locations.remove(loc);
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
