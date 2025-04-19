package com.metaverse.workflow.exceptions;

import java.util.List;

public class UserNotFoundException extends DataException {
    public UserNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public UserNotFoundException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }

    public UserNotFoundException(String message, String errorCode, int statusCode, List<String> errors) {
        super(message, errorCode, statusCode, errors);
    }
}
