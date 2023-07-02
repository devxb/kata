package xb.note.repository.database;

import xb.note.domain.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardDataBase {

    public static final List<Board> boards;
    public static final AtomicInteger autoIncrementBoardId;
    static{
        boards = new ArrayList<>();
        autoIncrementBoardId = new AtomicInteger(0);
    }

}
