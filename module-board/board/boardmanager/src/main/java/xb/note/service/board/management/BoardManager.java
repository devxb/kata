package xb.note.service.board.management;

import xb.note.service.board.management.dto.BoardDto;

import java.util.List;

public interface BoardManager {

    void createBoard(BoardDto request);

    List<BoardDto> findAllBoards();

    void deleteById(int id);

}
