package org.example.user.repository;

import java.util.Optional;
import org.example.core.meta.annotation.ServiceProvider;
import org.example.domain.entity.User;
import org.example.domain.spi.persistence.UserRepositoryServiceProvider;
import org.example.user.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@ServiceProvider
public class UserRepository implements UserRepositoryServiceProvider{

    private final UserJpaRepository userJpaRepository;

    @Autowired
    UserRepository(UserJpaRepository userJpaRepository){
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    @Transactional
    public void createUser(User user){
        org.example.data.User saveRequest = new org.example.data.User(user.getName());
        userJpaRepository.save(saveRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(int id){
        Optional<org.example.data.User> response = userJpaRepository.findById(id);
        if(response.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(User.builder().id(response.get().getId())
                .name(response.get().getName())
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByName(String name){
        Optional<org.example.data.User> response = userJpaRepository.findByName(name);
        if(response.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(User.builder().id(response.get().getId())
                .name(response.get().getName())
                .build());
    }

}
