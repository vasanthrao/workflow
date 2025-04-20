package com.metaverse.workflow.exceptions;

public class UserDetailsException extends DataException{
    public UserDetailsException(String message, String errorCode) {
        super(message, errorCode);
    }

    public UserDetailsException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }
}
