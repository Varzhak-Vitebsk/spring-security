package com.epam.edu.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Authority implements GrantedAuthority {
  INFO("INFO"),
  ADMIN("ADMIN");

  private final String value;

  @Override
  public String getAuthority() {
    return value;
  }
}
