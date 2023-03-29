package com.epam.edu.storage.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

  /**
   * Unique id
   */
  @Id
  @SequenceGenerator(name = "service_user_id_gen", sequenceName = "service_user_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "service_user_id_gen")
  private Long id;

  /**
   * User login
   */
  private String login;

  /**
   * User password
   */
  private String password;

  /**
   * User authorities
   */
  @Valid
  @NotNull
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "authority_user",
      joinColumns = { @JoinColumn(name = "user_id") },
      inverseJoinColumns = { @JoinColumn(name = "authority_id") }
  )
  @Builder.Default
  private Set<AuthorityEntity> authorities = new HashSet<>();

  public void addAuthority(AuthorityEntity authority) {
    authorities.add(authority);
    authority.getUsers().add(this);
  }

  public void removeAuthority(AuthorityEntity authority) {
    authorities.remove(authority);
    authority.getUsers().remove(this);
  }

}
