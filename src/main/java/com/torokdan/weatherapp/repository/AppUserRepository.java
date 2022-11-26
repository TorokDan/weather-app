package com.torokdan.weatherapp.repository;

import com.torokdan.weatherapp.model.entity.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

  boolean existsByUsername(String username);
  boolean existsByEmail(String email);
  Optional<AppUser> findByUsername(String username);
}
