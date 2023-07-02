package xb.note.controller.board.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthorRequest {

    @NotNull
    private int id;
    @NotEmpty
    private String name;

    public AuthorRequest(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

}
