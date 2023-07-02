package xb.note.service.board.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.note.service.board.management.dto.BoardDto;
import xb.note.service.board.management.spi.BoardRepository;

import java.util.List;

@Service
public class DefaultBoardManager implements BoardManager{

    private final BoardRepository boardRepository;

    @Override
    public void createBoard(BoardDto request) {
        boardRepository.createBoard(request);
    }

    @Override
    public List<BoardDto> findAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        boardRepository.deleteById(id);
    }

    @Autowired
    public DefaultBoardManager(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

}
