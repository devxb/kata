package xb.note.repository.user.management;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {InMemoryUserManagementRepository.class})
public class InMemoryUserManagementRepositoryTest {

    @Autowired
    private InMemoryUserManagementRepository repository;

    @BeforeEach
    @AfterEach
    public void DELETE_ALL_USER(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("유저 한명 이름 받아서 저장")
    public void CREATE_ONE_USER_TEST(){
        // given
        String name = "devxb";

        // when
        repository.create(name);

        // then
        Assertions.assertEquals(name, repository.read(name).getName());
    }

    @Test
    @DisplayName("유저 아이디 입력 받아서 삭제")
    public void DELETE_USER_TEST(){
        // given
        String name = "devxb";
        repository.create("devxb");
        int id = repository.read("devxb").getId();

        // when
        repository.delete(id);

        // then
        Assertions.assertThrows(IllegalStateException.class, ()-> repository.read("devxb"));
    }

}
