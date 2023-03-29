package com.epam.edu.config;

import com.epam.edu.model.Authority;
import com.epam.edu.storage.AuthorityRepository;
import com.epam.edu.storage.UserRepository;
import com.epam.edu.storage.model.AuthorityEntity;
import com.epam.edu.storage.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class WebSecurityConfig {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AuthorityRepository authorityRepository;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
      UserDetailsRepositoryReactiveAuthenticationManager authenticationManager) {
    return http.authorizeExchange()
        .pathMatchers("/login").permitAll()
        .pathMatchers("/lout").permitAll()
        .pathMatchers("/actuator/health").permitAll()
        .pathMatchers("/actuator/info").hasAuthority(Authority.INFO.getAuthority())
        .pathMatchers("/actuator/env").hasAuthority(Authority.ADMIN.getAuthority())
        .anyExchange().authenticated()
        .and()
        .authenticationManager(authenticationManager)
        .formLogin().loginPage("/login")
        .and()
        .logout().logoutUrl("/logout")
        .and()
        .exceptionHandling()
        .and()
        .csrf().disable()
        .build();
  }

  @Bean
  public UserDetailsRepositoryReactiveAuthenticationManager authenticationManager(
      ReactiveUserDetailsService userDetailsService) {
    var authManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    authManager.setPasswordEncoder(new BCryptPasswordEncoder(10));
    return authManager;
  }

  @Bean
  @ConditionalOnProperty(name = "database.example", havingValue = "true")
  public void exampleEntities() {
    setAuthorityRepository();
    setUserRepository();
  }

  private void setAuthorityRepository() {
    log.info("Setting authority");
    if (authorityRepository.count() == 0) {
      authorityRepository.save(
          AuthorityEntity.builder()
              .authority(Authority.INFO)
              .build());
      authorityRepository.save(
          AuthorityEntity.builder()
              .authority(Authority.ADMIN)
              .build());
      log.info("Authority set");
    }
  }

  private void setUserRepository() {
    log.info("Setting users");
    if (userRepository.count() == 0) {
      var encoder = new BCryptPasswordEncoder(10);
      var infoUser = UserEntity.builder()
          .login("info")
          .password(encoder.encode("info"))
          .build();
      var infoAuth = authorityRepository.findByAuthority(Authority.INFO);
      infoUser.addAuthority(infoAuth);
      userRepository.save(infoUser);
      var adminUser = UserEntity.builder()
          .login("admin")
          .password(encoder.encode("admin"))
          .build();
      var adminAuth = authorityRepository.findByAuthority(Authority.ADMIN);
      adminUser.addAuthority(adminAuth);
      adminUser.addAuthority(infoAuth);
      userRepository.save(adminUser);
      log.info("Users set");
    }
  }

}
