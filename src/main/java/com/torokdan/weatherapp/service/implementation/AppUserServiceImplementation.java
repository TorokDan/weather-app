package com.torokdan.weatherapp.service.implementation;

import com.torokdan.weatherapp.exception.EmailAlreadyExistsException;
import com.torokdan.weatherapp.exception.UsernameAlreadyExistsException;
import com.torokdan.weatherapp.model.AppUser;
import com.torokdan.weatherapp.model.dto.AppUserRequestDto;
import com.torokdan.weatherapp.model.dto.AppUserResponseDto;
import com.torokdan.weatherapp.repository.AppUserRepository;
import com.torokdan.weatherapp.service.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImplementation implements AppUserService {

  private final AppUserRepository userRepository;

  public AppUserServiceImplementation(AppUserRepository userRepository) {

    this.userRepository = userRepository;
  }

  @Override
  public AppUserResponseDto createUser(AppUserRequestDto request) {
    checkUserNameAndEmail(request);
    AppUser user = AppUser.from(request);
    userRepository.save(user);
    return AppUserResponseDto.from(user);
  }

  private void checkUserNameAndEmail(AppUserRequestDto toCheck) {
    if (userRepository.existsByUsername(toCheck.getUsername())) {
      throw new UsernameAlreadyExistsException();
    }
    if (userRepository.existsByEmail(toCheck.getEmail())) {
      throw new EmailAlreadyExistsException();
    }
  }
}
