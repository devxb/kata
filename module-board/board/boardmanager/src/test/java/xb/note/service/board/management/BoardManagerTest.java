package xb.note.service.board.management;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xb.note.service.board.management.dto.BoardDto;
import xb.note.service.board.management.spi.BoardRepository;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DefaultBoardManager.class})
public class BoardManagerTest {

    @Autowired
    private BoardManager boardManager;

    @MockBean
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 생성 성공 테스트")
    public void CREATE_BOARD_SUCCESS_TEST(){
        // given
        BoardDto boardCreateRequest = new BoardDto("hello world", "this is article",
                new BoardDto.AuthorDto(1, "devxb"));

        // when
        Mockito.when(boardRepository.findAll()).thenReturn(List.of(boardCreateRequest));

        boardManager.createBoard(boardCreateRequest);
        List<BoardDto> boards = boardManager.findAllBoards();

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, boards.size()),
                ()-> Assertions.assertEquals("this is article", boards.get(0).getArticle())
        );
    }

    @Test
    @DisplayName("게시글 생성 실패 \"없는유저\" 테스트")
    public void CREATE_BOARD_FAIL_TEST(){
        // given
        BoardDto boardCreateRequest = new BoardDto("hello world", "this is article",
                new BoardDto.AuthorDto(1, "devxb"));

        // when
        Mockito.doThrow(new IllegalStateException("Can not find right user named \"devxb\" id \"1\"")).when(boardRepository).createBoard(boardCreateRequest);

        // then
        Assertions.assertThrows(IllegalStateException.class, ()-> boardManager.createBoard(boardCreateRequest));
    }

    @Test
    @DisplayName("게시글 삭제 성공 테스트")
    public void DELETE_BOARD_SUCCESS_TEST(){
        // given
        BoardDto boardCreateRequest = new BoardDto("hello world", "this is article",
                new BoardDto.AuthorDto(1, "devxb"));
        boardCreateRequest.setId(1);

        // when
        Mockito.when(boardRepository.findAll()).thenReturn(List.of(boardCreateRequest));
        boardManager.createBoard(boardCreateRequest);
        int id = boardManager.findAllBoards().get(0).getId();

        // then
        Assertions.assertDoesNotThrow(()-> boardManager.deleteById(id));
    }

}
