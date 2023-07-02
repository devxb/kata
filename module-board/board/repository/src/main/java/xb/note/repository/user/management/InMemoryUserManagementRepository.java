package xb.note.repository.user.management;

import org.springframework.stereotype.Service;
import xb.note.domain.User;
import xb.note.repository.database.UserDataBase;
import xb.note.service.user.management.dto.UserDto;
import xb.note.service.user.management.spi.UserManagementRepository;

import java.util.Map;

@Service
public class InMemoryUserManagementRepository implements UserManagementRepository{

    @Override
    public void create(String name) {
        UserDataBase.users.put(name, new User(UserDataBase.autoIncrementUserId.getAndIncrement(), name));
    }

    @Override
    public UserDto read(String name) {
        User user = UserDataBase.users.get(name);
        throwIfCannotFindUser(user);
        return new UserDto(user.getId(), user.getName());
    }

    private void throwIfCannotFindUser(User user){
        if(user == null) throw new IllegalStateException("Can not find right user");
    }

    @Override
    public void delete(int id) {
        for(Map.Entry<String, User> entry : UserDataBase.users.entrySet()){
            if(entry.getValue().getId() == id){
                UserDataBase.users.remove(entry.getKey());
                return;
            }
        }
    }

    public void deleteAll(){
        UserDataBase.users.clear();
    }

}
