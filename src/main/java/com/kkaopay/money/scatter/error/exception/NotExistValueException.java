package com.kkaopay.money.scatter.error.exception;

import com.kkaopay.money.scatter.error.ErrorCode;

public class NotExistValueException extends MoneyScatterException {

    public NotExistValueException() {
        super(ErrorCode.ENTITY_NOT_FOUND);
    }

    public NotExistValueException(final String message) {
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }

    public NotExistValueException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
