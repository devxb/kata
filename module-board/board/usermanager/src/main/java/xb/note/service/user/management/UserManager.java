package xb.note.service.user.management;

import xb.note.service.user.management.dto.UserDto;

public interface UserManager {

    void createUser(String name);
    UserDto readUser(String name);
    void deleteUser(int id);

}
