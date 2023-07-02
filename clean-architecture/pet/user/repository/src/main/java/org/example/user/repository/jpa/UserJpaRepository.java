package org.example.user.repository.jpa;

import java.util.Optional;
import org.example.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer>{

    Optional<User> findByName(String name);

}
