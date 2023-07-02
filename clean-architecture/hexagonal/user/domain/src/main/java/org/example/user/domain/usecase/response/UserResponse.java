package org.example.user.domain.usecase.response;

public final class UserResponse{

    private final int id;
    private final String name;

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
