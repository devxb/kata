package xb.note.service.user.management.dto;

public class UserDto{
    private int id;
    private String name;

    public UserDto(int id, String name){
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
