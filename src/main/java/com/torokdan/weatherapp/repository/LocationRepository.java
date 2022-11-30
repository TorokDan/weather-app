package com.torokdan.weatherapp.repository;

import com.torokdan.weatherapp.model.entity.Location;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

  boolean existsByName(String name);
  Optional<Location> findByName(String name);
}
