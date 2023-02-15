package com.example.spring_security.ApiException;

public class ApiException extends  RuntimeException {
    public ApiException(String message){
        super(message);
    }
}
