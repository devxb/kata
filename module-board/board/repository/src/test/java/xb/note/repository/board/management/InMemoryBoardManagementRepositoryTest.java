package xb.note.repository.board.management;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xb.note.repository.user.management.InMemoryUserManagementRepository;
import xb.note.service.board.management.dto.BoardDto;
import xb.note.service.user.management.dto.UserDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {InMemoryBoardManagementRepository.class, InMemoryUserManagementRepository.class})
public class InMemoryBoardManagementRepositoryTest {

    @Autowired
    private InMemoryBoardManagementRepository boardRepository;
    @Autowired
    private InMemoryUserManagementRepository userRepository;

    private Queue<Integer> deleteUserQueue = new LinkedList<>();

    @BeforeEach
    @AfterEach
    public void DELETE_ALL(){
        userRepository.deleteAll();
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("보드 저장, 조회 테스트")
    public void SAVE_AND_READ_BOARD_TEST(){
        // given
        userRepository.create("devxb");
        UserDto user = userRepository.read("devxb");
        BoardDto boardCreateRequest = new BoardDto("hello world", "this is article",
                new BoardDto.AuthorDto(user.getId(), "devxb"));

        // when
        boardRepository.createBoard(boardCreateRequest);
        List<BoardDto> boards = boardRepository.findAll();

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, boards.size()),
                ()-> Assertions.assertEquals("hello world", boardCreateRequest.getTitle())
        );
    }

    @Test
    @DisplayName("보드 저장 실패 \"저장되지 않은 유저\" 테스트")
    public void SAVE_BOARD_FAIL_TEST(){
        // given
        BoardDto boardCreateRequest = new BoardDto("hello world", "this is article",
                new BoardDto.AuthorDto(1, "devxb"));

        // then
        Assertions.assertThrows(IllegalStateException.class, ()-> boardRepository.createBoard(boardCreateRequest));
    }

    @Test
    @DisplayName("보드 삭제 테스트")
    public void DELETE_BOARD_TEST(){
        // given
        userRepository.create("devxb");
        UserDto user = userRepository.read("devxb");
        BoardDto boardCreateRequest = new BoardDto("hello world", "this is article",
                new BoardDto.AuthorDto(user.getId(), "devxb"));

        // when
        boardRepository.createBoard(boardCreateRequest);
        int savedBoardId = boardRepository.findAll().get(0).getId();
        boardRepository.deleteById(savedBoardId);

        // then
        Assertions.assertEquals(0, boardRepository.findAll().size());
    }

}
