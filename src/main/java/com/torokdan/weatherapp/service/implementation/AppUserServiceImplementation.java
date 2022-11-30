package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.exception.EmailAlreadyExistsException;
import com.torokdan.weatherapp.exception.NoUserFoundException;
import com.torokdan.weatherapp.exception.UsernameAlreadyExistsException;
import com.torokdan.weatherapp.model.dto.DeleteLocationFromUserResponseDto;
import com.torokdan.weatherapp.model.entity.AppUser;
import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
import com.torokdan.weatherapp.model.entity.Location;
import com.torokdan.weatherapp.repository.AppUserRepository;
import com.torokdan.weatherapp.service.AppUserService;
import com.torokdan.weatherapp.service.ModelCreator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImplementation implements AppUserService, UserDetailsService {

  private final AppUserRepository userRepository;
  private final ModelCreator modelCreator;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public AppUserServiceImplementation(AppUserRepository userRepository, ModelCreator modelCreator,
      BCryptPasswordEncoder bCryptPasswordEncoder) {

    this.userRepository = userRepository;
    this.modelCreator = modelCreator;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public AppUserResponseDto createUser(AppUserRequestDto request) {
    checkUserNameAndEmail(request);
    AppUser user = modelCreator.createAppUser(request);
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return modelCreator.createAppUserResponseDto(user);
  }

  @Override
  public List<AppUserResponseDto> listUsers() {
    List<AppUserResponseDto> users = userRepository.findAll().stream().map(modelCreator::createAppUserResponseDto).toList();
    if (users.size() == 0) {
      throw new NoUserFoundException();
    }
    return users;
  }

  @Override
  public void addLocationToUser(String userName, Location location) {
    AppUser user = userRepository.findByUsername(userName)
        .orElseThrow(() ->new UsernameNotFoundException(userName));
    user.addLocation(location);
    userRepository.save(user);
  }

  @Override
  public AppUser findUser(String name) {
    return userRepository.findByUsername(name)
        .orElseThrow(() -> new UsernameNotFoundException(name));
  }

  @Override
  public DeleteLocationFromUserResponseDto removeLocationFromUser(String userName, Location location) {
    AppUser user = findUser(userName);
    user.removeLocation(location);
    userRepository.save(user);
    return modelCreator.createDeleteLocationFromUserResponseDto(user, location);
  }

  private void checkUserNameAndEmail(AppUserRequestDto toCheck) {
    if (userRepository.existsByUsername(toCheck.getUsername())) {
      throw new UsernameAlreadyExistsException(toCheck.getUsername());
    }
    if (userRepository.existsByEmail(toCheck.getEmail())) {
      throw new EmailAlreadyExistsException(toCheck.getEmail());
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser appUser = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(appUser.getRole().getName()));
    return new User(appUser.getUsername(), appUser.getPassword(), authorities);
  }
}
