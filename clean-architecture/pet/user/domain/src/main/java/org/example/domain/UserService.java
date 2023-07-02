package org.example.domain;

import org.example.domain.entity.User;

public interface UserService{

    void createUser(String name);

    User findById(int id);

    User findByName(String name);

}
