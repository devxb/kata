package xb.note.controller.board.response;

public class AuthorResponse {

    private final int id;
    private final String name;

    public AuthorResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

}
