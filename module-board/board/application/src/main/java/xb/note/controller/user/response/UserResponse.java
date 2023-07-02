package xb.note.controller.user.response;

public class UserResponse {

    private int id;
    private String name;

    public UserResponse(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void getName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

}
