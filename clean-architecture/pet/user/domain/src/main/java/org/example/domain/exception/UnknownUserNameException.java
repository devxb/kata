package org.example.domain.exception;

public class UnknownUserNameException extends RuntimeException{

    public UnknownUserNameException(String name){
        super("Unknown user name \"" + name + "\"");
    }

    @Override
    public synchronized Throwable fillInStackTrace(){
        return this;
    }

}
