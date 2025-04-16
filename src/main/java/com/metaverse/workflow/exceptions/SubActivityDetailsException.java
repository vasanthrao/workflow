package com.metaverse.workflow.exceptions;

public class SubActivityDetailsException extends DataException {

    public SubActivityDetailsException(String message, String errorCode) {
        super(message, errorCode);
    }

    public SubActivityDetailsException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }
}
