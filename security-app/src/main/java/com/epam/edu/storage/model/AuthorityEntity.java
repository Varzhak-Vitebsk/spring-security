package com.epam.edu.storage.model;

import com.epam.edu.model.Authority;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

@Entity
@Table(name = "service_authority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorityEntity {

  /**
   * Unique id
   */
  @NotNull
  @Id
  @SequenceGenerator(name = "service_authority_id_gen", sequenceName = "service_authority_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "service_authority_id_gen")
  private Long id;

  /**
   * Users with authority
   */
  @Valid
  @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
  @Builder.Default
  private Set<UserEntity> users = new HashSet<>();

  /**
   * Authority type
   */
  @Enumerated(value = EnumType.STRING)
  private Authority authority;

}
