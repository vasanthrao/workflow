package com.metaverse.workflow.exceptions;

public class ActivityDetailsException extends DataException {

    public ActivityDetailsException(String message, String errorCode) {
        super(message, errorCode);
    }

    public ActivityDetailsException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }
}