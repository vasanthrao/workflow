package com.metaverse.workflow.exceptions;

public class AgencyDetailsException  extends DataException{
    public AgencyDetailsException(String message, String errorCode) {
        super(message, errorCode);
    }

    public AgencyDetailsException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }
}
