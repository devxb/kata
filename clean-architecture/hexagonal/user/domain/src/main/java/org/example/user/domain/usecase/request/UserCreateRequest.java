package org.example.user.domain.usecase.request;

public final class UserCreateRequest{

    private final String name;

    public UserCreateRequest(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
