package com.kkaopay.money.scatter.error.exception;

import com.kkaopay.money.scatter.error.ErrorCode;

public class InvalidValueException  extends MoneyScatterException {

    public InvalidValueException() {
        super(ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidValueException(final String message, final ErrorCode errorCode) {
        super(message, errorCode);
    }
}
