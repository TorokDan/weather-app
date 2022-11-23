package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.exception.EmailAlreadyExistsException;
import com.torokdan.weatherapp.exception.NoUserFoundException;
import com.torokdan.weatherapp.exception.UsernameAlreadyExistsException;
import com.torokdan.weatherapp.model.AppUser;
import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
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
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImplementation implements AppUserService, UserDetailsService {

  private final AppUserRepository userRepository;
  private final ModelCreator modelCreator;

  public AppUserServiceImplementation(AppUserRepository userRepository, ModelCreator modelCreator) {

    this.userRepository = userRepository;
    this.modelCreator = modelCreator;
  }

  @Override
  public AppUserResponseDto createUser(AppUserRequestDto request) {
    checkUserNameAndEmail(request);
    AppUser user = modelCreator.createAppUser(request);
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
