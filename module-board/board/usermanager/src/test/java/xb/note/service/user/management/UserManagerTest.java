package xb.note.service.user.management;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xb.note.service.user.management.dto.UserDto;
import xb.note.service.user.management.spi.UserManagementRepository;

import java.util.LinkedList;
import java.util.Queue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DefaultUserManager.class})
public class UserManagerTest {

    @Autowired
    @Qualifier("defaultUserManager")
    private UserManager userManager;

    @MockBean
    private UserManagementRepository userManagementRepository;

    private Queue<UserDto> deleteQueue = new LinkedList<>();

    @Test
    @DisplayName("새로운 유저 생성")
    public void CREATE_USER_TEST(){
        // given
        String name = "devxb";

        // when
        Mockito.when(userManagementRepository.read(Mockito.anyString())).thenReturn(new UserDto(1, name));

        userManager.createUser("devxb");
        UserDto user = userManager.readUser("devxb");
        deleteQueue.add(user);

        // then
        Assertions.assertEquals(name, user.getName());
    }

    @BeforeEach
    @AfterEach
    public void deleteAllUser(){
        for(UserDto user : deleteQueue)
            userManager.deleteUser(user.getId());
    }
}
