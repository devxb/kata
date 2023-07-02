package xb.note.service.user.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.note.service.user.management.dto.UserDto;
import xb.note.service.user.management.spi.UserManagementRepository;

@Service
public class DefaultUserManager implements UserManager{

    private final UserManagementRepository userManagementRepository;

    @Override
    public void createUser(String name) {
        userManagementRepository.create(name);
    }

    @Override
    public UserDto readUser(String name) {
        return userManagementRepository.read(name);
    }

    @Override
    public void deleteUser(int id) {
        userManagementRepository.delete(id);
    }

    @Autowired
    public DefaultUserManager(UserManagementRepository userManagementRepository){
        this.userManagementRepository = userManagementRepository;
    }

}
