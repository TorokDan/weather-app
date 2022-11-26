package com.torokdan.weatherapp.model.entity;

import com.torokdan.weatherapp.model.RoleType;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @OneToMany
  private List<AppUser> users;

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Role(RoleType name) {
    this.name = name.toString();
  }

  public Role() {

  }
}
