package com.metaverse.workflow.exceptions;

public class ProgramDetailsException extends DataException {

    public ProgramDetailsException(String message, String errorCode) {
        super(message, errorCode);
    }

    public ProgramDetailsException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }
}