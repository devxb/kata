package xb.note.service.user.management.spi;

import xb.note.service.user.management.dto.UserDto;

public interface UserManagementRepository {

    void create(String name);

    UserDto read(String name);

    void delete(int id);

}
