package com.kkaopay.money.scatter.error.exception;

import com.kkaopay.money.scatter.error.ErrorMessage;

public class NotExistValueException extends RuntimeException {

    public NotExistValueException() {
        super(ErrorMessage.NOT_EXIST_VALUE);
    }
}
