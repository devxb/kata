package org.example.controller.response;

public class UserResponse{

    private int id;
    private String name;

    UserResponse(){}

    public UserResponse(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

}
