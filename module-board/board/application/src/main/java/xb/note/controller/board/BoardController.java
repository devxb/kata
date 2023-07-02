package xb.note.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xb.note.controller.board.request.BoardRequest;
import xb.note.controller.board.response.AuthorResponse;
import xb.note.controller.board.response.BoardResponse;
import xb.note.service.board.management.BoardManager;
import xb.note.service.board.management.dto.BoardDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BoardController {

    private final BoardManager boardManager;

    @GetMapping("/article")
    public List<BoardResponse> readBoards(){
        List<BoardDto> boards = boardManager.findAllBoards();
        List<BoardResponse> response = new ArrayList<>();
        for(BoardDto board : boards){
            BoardResponse boardResponse = new BoardResponse(board.getId(), board.getTitle(), board.getArticle(), board.getCreatedAt(),
                    new AuthorResponse(board.getAuthor().getId(), board.getAuthor().getName()));
            response.add(boardResponse);
        }
        return response;
    }

    @PostMapping("/article")
    public void saveArticle(@RequestBody BoardRequest boardRequest){
        BoardDto boardDto = new BoardDto(boardRequest.getTitle(), boardRequest.getArticle(),
                new BoardDto.AuthorDto(boardRequest.getAuthor().getId(), boardRequest.getAuthor().getName()));
        boardManager.createBoard(boardDto);
    }

    @DeleteMapping("/article/{article-id}")
    public void deleteArticle(@PathVariable("article-id") int id){
        boardManager.deleteById(id);
    }

    @Autowired
    public BoardController(BoardManager boardManager){
        this.boardManager = boardManager;
    }

}
