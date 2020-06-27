package com.kkaopay.money.scatter.error.exception;

import com.kkaopay.money.scatter.error.ErrorCode;

public class MoneyScatterException extends RuntimeException {

    private final ErrorCode errorCode;

    public MoneyScatterException(final String message, final ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MoneyScatterException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
