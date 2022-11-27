package com.torokdan.weatherapp.model.entity;

import com.torokdan.weatherapp.model.RoleType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;

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
