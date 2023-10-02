package com.example.crud_fstack.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("could not find with user id: "+id);
    }
}
