package com.epam.edu.storage.model;

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
@Table(name = "data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Builder
public class DataEntity {

  @Id
  @SequenceGenerator(name = "data_id_gen", sequenceName = "data_id_seq", allocationSize = 1)
  @GeneratedValue(generator = "data_id_gen")
  private Long id;
  private String name;

}
