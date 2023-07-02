package xb.note.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xb.note.controller.user.request.UserSaveRequest;
import xb.note.controller.user.response.UserResponse;
import xb.note.service.user.management.UserManager;
import xb.note.service.user.management.dto.UserDto;

@RestController
public class UserController {

    private final UserManager userManager;

    @PostMapping("/user")
    public void saveUser(@RequestBody UserSaveRequest userSaveRequest){
        userManager.createUser(userSaveRequest.getName());
    }

    @GetMapping("/user/{name}")
    public UserResponse getUser(@PathVariable("name") String name){
        UserDto userDto = userManager.readUser(name);
        return new UserResponse(userDto.getId(), userDto.getName());
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userManager.deleteUser(id);
    }

    @Autowired
    public UserController(UserManager userManager){
        this.userManager = userManager;
    }

}
