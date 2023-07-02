package org.example.domain.exception;

public class DuplicateUserNameException extends RuntimeException{

    public DuplicateUserNameException(String name){
        super("Duplicated user name \"" + name + "\"");
    }

    @Override
    public synchronized Throwable fillInStackTrace(){
        return this;
    }

}
