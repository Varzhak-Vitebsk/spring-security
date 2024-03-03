package com.epam.edu.service;

import com.epam.edu.storage.UserRepository;
import com.epam.edu.storage.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  public UserEntity getUser(String login) {
    return repository.findByLogin(login)
        .orElseThrow(() -> new UsernameNotFoundException("Username %s not found".formatted(login)));
  }

}
