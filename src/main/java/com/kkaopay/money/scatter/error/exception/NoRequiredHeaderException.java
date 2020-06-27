package com.kkaopay.money.scatter.error.exception;

import com.kkaopay.money.scatter.error.ErrorCode;

public class NoRequiredHeaderException extends MoneyScatterException {

    public NoRequiredHeaderException() {
        super(ErrorCode.NO_REQUIRED_HEADER_INFO);
    }

    public NoRequiredHeaderException(final String message) {
        super(message, ErrorCode.NO_REQUIRED_HEADER_INFO);
    }
}
