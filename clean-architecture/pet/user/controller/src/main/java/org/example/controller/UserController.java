package org.example.controller;

import javax.websocket.server.PathParam;
import org.example.controller.response.UserResponse;
import org.example.domain.UserService;
import org.example.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController{

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathParam("id") int id){
        User user = userService.findById(id);
        return new UserResponse(user.getId(), user.getName());
    }

    @PostMapping("/users")
    public void createUser(@RequestParam("name") String name){
        userService.createUser(name);
    }

}
