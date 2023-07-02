package org.example.user.repository.jpa;

import org.example.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer>{
}
