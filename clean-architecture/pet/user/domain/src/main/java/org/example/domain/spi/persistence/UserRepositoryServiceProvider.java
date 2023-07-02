package org.example.domain.spi.persistence;

import java.util.Optional;
import org.example.domain.entity.User;

public interface UserRepositoryServiceProvider{

    void createUser(User user);

    Optional<User> findById(int id);

    Optional<User> findByName(String name);

}
