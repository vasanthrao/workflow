package com.metaverse.workflow.exceptions;

import java.util.List;

public class AgencyDetailsException extends DataException {

    public AgencyDetailsException(String message, String errorCode) {
        super(message, errorCode);
    }

    public AgencyDetailsException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }

    public AgencyDetailsException(String message, String errorCode, int statusCode, List<String> errors) {
        super(message, errorCode, statusCode, errors);
    }
}
