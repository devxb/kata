package org.example.domain.entity;

public class User{

    private final int id;
    private final String name;

    private User(){
        throw new UnsupportedOperationException("Cannot invoke constructor \"User()\"");
    }

    private User(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    public static Builder builder(){
        return new Builder();
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public static class Builder{

        private int id;
        private String name;

        private Builder(){}

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }

}
