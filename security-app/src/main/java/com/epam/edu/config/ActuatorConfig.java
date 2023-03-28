package com.epam.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class ActuatorConfig {

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(
      ServerHttpSecurity http) {
    return http.authorizeExchange()
        .pathMatchers("/actuator/health").permitAll()
        .pathMatchers("/actuator/info").hasAuthority(Authority.INFO.name())
        .pathMatchers("/actuator/env").hasAuthority(Authority.ADMIN.name())
        .anyExchange().authenticated()
        .and().build();
  }

}
