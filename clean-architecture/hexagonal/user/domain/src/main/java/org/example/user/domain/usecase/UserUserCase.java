package org.example.user.domain.usecase;

import org.example.core.meta.UseCase;
import org.example.user.domain.entity.User;
import org.example.user.domain.port.in.UserUseCasePort;
import org.example.user.domain.port.out.UserRepositoryPort;
import org.example.user.domain.usecase.request.UserCreateRequest;
import org.example.user.domain.usecase.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class UserUserCase implements UserUseCasePort{

    private final UserRepositoryPort userRepositoryPort;

    @Autowired
    public UserUserCase(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void createUser(UserCreateRequest userCreateRequest){
        userRepositoryPort.createUser(userCreateRequest);
    }

    @Override
    public UserResponse findById(int id){
        UserResponse response = userRepositoryPort.findById(id);
        return new UserResponse(response.getId(), response.getName());
    }

}
