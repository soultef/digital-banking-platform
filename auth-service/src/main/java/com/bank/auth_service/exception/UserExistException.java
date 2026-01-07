package com.bank.auth_service.exception;

public class UserExistException extends  RuntimeException{

    public UserExistException(String email) {
        super("Email already registered: " + email);
    }
}
