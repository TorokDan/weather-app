package com.torokdan.weatherapp.repository;

import com.torokdan.weatherapp.model.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  boolean existsByName(String name);
  Optional<Role> findByName(String name);
}