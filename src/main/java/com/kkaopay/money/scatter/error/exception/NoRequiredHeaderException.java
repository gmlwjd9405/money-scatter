package com.kkaopay.money.scatter.error.exception;

import com.kkaopay.money.scatter.error.ErrorMessage;

public class NoRequiredHeaderException extends RuntimeException {

    public NoRequiredHeaderException() {
        super(ErrorMessage.NO_REQUIRED_HEADER_INFO);
    }
}
