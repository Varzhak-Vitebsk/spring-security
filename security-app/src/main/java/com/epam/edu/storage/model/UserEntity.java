package com.epam.edu.storage.model;

import com.epam.edu.config.Authority;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "service_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Builder
public class UserEntity {

  @Id
  @SequenceGenerator(name = "service_user_id_gen", sequenceName = "service_user_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "service_user_id_gen")
  private Long id;
  private String login;
  private String password;
  @Builder.Default
  @ElementCollection(targetClass=Authority.class)
  private Set<Authority> authorities = new HashSet<>();

}
