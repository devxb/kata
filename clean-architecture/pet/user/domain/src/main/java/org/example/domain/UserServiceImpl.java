package org.example.domain;

import java.util.Optional;
import org.example.core.meta.annotation.Domain;
import org.example.domain.exception.DuplicateUserNameException;
import org.example.domain.exception.UnknownUserIdException;
import org.example.domain.exception.UnknownUserNameException;
import org.example.domain.spi.persistence.UserRepositoryServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.domain.entity.User;

@Domain
public class UserServiceImpl implements UserService{

    private final UserRepositoryServiceProvider userRepositoryServiceProvider;

    @Autowired
    UserServiceImpl(UserRepositoryServiceProvider userRepositoryServiceProvider){
        this.userRepositoryServiceProvider = userRepositoryServiceProvider;
    }

    @Override
    public void createUser(String name){
        throwIfDuplicatedUserName(name);
        User user = User.builder()
                .name(name)
                .build();
        userRepositoryServiceProvider.createUser(user);
    }

    private void throwIfDuplicatedUserName(String name){
        Optional<User> user = userRepositoryServiceProvider.findByName(name);
        if(user.isPresent()){
            throw new DuplicateUserNameException(name);
        }
    }

    @Override
    public User findById(int id){
        return userRepositoryServiceProvider.findById(id).orElseThrow(
                () -> {throw new UnknownUserIdException(id);}
        );
    }

    @Override
    public User findByName(String name){
        return userRepositoryServiceProvider.findByName(name).orElseThrow(
                () -> {throw new UnknownUserNameException(name);}
        );
    }

}
