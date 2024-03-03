package com.epam.edu.storage;

import com.epam.edu.model.Authority;
import com.epam.edu.storage.model.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

  AuthorityEntity findByAuthority(Authority authority);

}
