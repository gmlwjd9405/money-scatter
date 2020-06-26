package com.kkaopay.money.scatter.error.exception;

import com.kkaopay.money.scatter.error.ErrorMessage;

public class UnAuthorizationException extends RuntimeException {

    public UnAuthorizationException() {
        super(ErrorMessage.UN_AUTHORIZATION);
    }
}
