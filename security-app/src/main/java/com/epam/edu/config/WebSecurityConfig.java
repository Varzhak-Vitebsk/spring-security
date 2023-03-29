package com.epam.edu.config;

import com.epam.edu.model.Authority;
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
public class WebSecurityConfig {

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
      UserDetailsRepositoryReactiveAuthenticationManager authenticationManager) {
    return http.authorizeExchange()
        .pathMatchers("/actuator/health").permitAll()
        .pathMatchers("/actuator/info").hasAuthority(Authority.INFO.getAuthority())
        .pathMatchers("/actuator/env").hasAuthority(Authority.ADMIN.getAuthority())
        .anyExchange().authenticated()
        .and()
        .authenticationManager(authenticationManager)
        .formLogin()
        .and()
        .logout()
        .and()
        .exceptionHandling()
        .and()
        .build();
  }

  @Bean
  public UserDetailsRepositoryReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService) {
    var authManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    authManager.setPasswordEncoder(new BCryptPasswordEncoder(10));
    return authManager;

  }

}
