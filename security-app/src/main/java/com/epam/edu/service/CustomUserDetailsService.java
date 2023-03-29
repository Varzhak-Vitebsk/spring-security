package com.epam.edu.service;

import com.epam.edu.storage.model.AuthorityEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements ReactiveUserDetailsService {

  private final UserService userService;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    log.debug("User {} identification", username);
    return Mono.just(username)
        .map(userService::getUser)
        .map(user -> User.withUsername(user.getLogin())
            .password(user.getPassword())
            .authorities(user.getAuthorities().stream().map(AuthorityEntity::getAuthority).toList())
            .build())
        .onErrorResume(Mono::error);
  }
}
