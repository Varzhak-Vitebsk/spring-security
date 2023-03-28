package com.epam.edu.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Authority {
  INFO("INFO"),
  ADMIN("ADMIN");

  private final String value;
}
