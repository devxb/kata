package org.example.user.domain.port.in;

import org.example.user.domain.usecase.request.UserCreateRequest;
import org.example.user.domain.usecase.response.UserResponse;

public interface UserUseCasePort{

    void createUser(UserCreateRequest userCreateRequest);

    UserResponse findById(int id);

}
