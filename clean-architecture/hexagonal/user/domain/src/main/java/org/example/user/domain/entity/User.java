package org.example.user.domain.entity;

public final class User{

    private final int id;
    private final String name;

    public User(int id, String name){
        System.out.println("레거시 테스트");
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
