package com.metaverse.workflow.exceptions;

import java.util.List;

public class HeadOfExpenseException extends DataException{

    public HeadOfExpenseException(String message, String errorCode) {
        super(message, errorCode);
    }

    public HeadOfExpenseException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }

    public HeadOfExpenseException(String message, String errorCode, int statusCode, List<String> errors) {
        super(message, errorCode, statusCode, errors);
    }
}
