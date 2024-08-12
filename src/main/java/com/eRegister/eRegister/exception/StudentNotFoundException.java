package com.eRegister.eRegister.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String massage){
        super(massage);
    }
}
