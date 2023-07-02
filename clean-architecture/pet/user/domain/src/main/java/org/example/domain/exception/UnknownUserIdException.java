package org.example.domain.exception;

public class UnknownUserIdException extends RuntimeException{

    public UnknownUserIdException(int id){
        super("Unknown user id \"" + id + "\"");
    }

    @Override
    public synchronized Throwable fillInStackTrace(){
        return this;
    }

}
