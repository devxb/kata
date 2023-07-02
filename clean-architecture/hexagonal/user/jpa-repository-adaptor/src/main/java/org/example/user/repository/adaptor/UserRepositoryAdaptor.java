package org.example.user.repository.adaptor;

import org.example.core.meta.Adaptor;
import org.example.user.domain.port.out.UserRepositoryPort;
import org.example.user.domain.usecase.request.UserCreateRequest;
import org.example.user.domain.usecase.response.UserResponse;
import org.example.user.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Adaptor
public class UserRepositoryAdaptor implements UserRepositoryPort{

    private final UserJpaRepository userJpaRepository;

    @Autowired
    UserRepositoryAdaptor(UserJpaRepository userJpaRepository){
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    @Transactional
    public void createUser(UserCreateRequest createRequest){
        org.example.persistence.User user = new org.example.persistence.User(createRequest.getName());
        userJpaRepository.save(user);
    }

    @Override
    @Transactional
    public UserResponse findById(int id){
        org.example.persistence.User finded = userJpaRepository.findById(id).get();
        return new UserResponse(finded.getId(), finded.getName());
    }

}
