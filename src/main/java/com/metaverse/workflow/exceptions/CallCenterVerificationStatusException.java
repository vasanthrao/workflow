package com.metaverse.workflow.exceptions;

import java.util.List;

public class CallCenterVerificationStatusException extends DataException {
    public CallCenterVerificationStatusException(String message, String errorCode) {
        super(message, errorCode);
    }

    public CallCenterVerificationStatusException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }

    public CallCenterVerificationStatusException(String message, String errorCode, int statusCode, List<String> errors) {
        super(message, errorCode, statusCode, errors);
    }
}
