package xb.note.repository.board.management;

import org.springframework.stereotype.Service;
import xb.note.domain.Board;
import xb.note.domain.User;
import xb.note.repository.database.BoardDataBase;
import xb.note.repository.database.UserDataBase;
import xb.note.service.board.management.dto.BoardDto;
import xb.note.service.board.management.spi.BoardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryBoardManagementRepository implements BoardRepository {

    @Override
    public void createBoard(BoardDto request) {
        throwIfNoExistUser(request.getAuthor());

        Board board = new Board(BoardDataBase.autoIncrementBoardId.getAndIncrement(),
                                request.getTitle(),
                                request.getArticle(),
                                new User(request.getAuthor().getId(), request.getAuthor().getName()));
        BoardDataBase.boards.add(board);
    }

    private void throwIfNoExistUser(BoardDto.AuthorDto author){
        if(!UserDataBase.users.containsKey(author.getName()))
            throw new IllegalStateException("Can not find right user named \"" + author.getName() + "\"");
    }

    @Override
    public List<BoardDto> findAll() {
        List<BoardDto> boardDtos = new ArrayList<>();
        for(Board board : BoardDataBase.boards){
            BoardDto boardDto = new BoardDto(board.getTitle(), board.getArticle(), new BoardDto.AuthorDto(board.getUser().getId(), board.getUser().getName()));
            boardDto.setId(board.getId());
            boardDto.setCreatedAt(board.getCreatedAt());
            boardDtos.add(boardDto);
        }
        return boardDtos;
    }

    @Override
    public void deleteById(int id) {
        for(Board board : BoardDataBase.boards){
            if(board.getId() == id) {
                BoardDataBase.boards.remove(board);
                return;
            }
        }
    }

    public void deleteAll(){
        BoardDataBase.boards.clear();
    }

}
