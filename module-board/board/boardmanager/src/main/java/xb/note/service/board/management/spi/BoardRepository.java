package xb.note.service.board.management.spi;

import xb.note.service.board.management.dto.BoardDto;

import java.util.List;

public interface BoardRepository {

    void createBoard(BoardDto request);

    List<BoardDto> findAll();

    void deleteById(int id);

}
