package com.springboot_crud.Exception;

public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
