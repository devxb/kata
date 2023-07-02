package org.example.user.controller;

import org.example.user.domain.port.in.UserUseCasePort;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController{

    private final UserUseCasePort useCasePort;

    UserController(UserUseCasePort useCasePort){
        this.useCasePort = useCasePort;
    }

    /*
        Do something...
     */

}
