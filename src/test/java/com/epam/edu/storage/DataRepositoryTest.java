package com.epam.edu.storage;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.edu.storage.model.DataEntity;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("DEV")
class DataRepositoryTest {

  @Autowired
  private DataRepository repository;

  @Test
  public void whenCalledSave_thenCorrectNumberOfEntities() {
    repository.save(DataEntity.builder()
        .name("test data")
        .build());
    List<DataEntity> users = repository.findAll();

    assertThat(users.size()).isEqualTo(1);
  }

}