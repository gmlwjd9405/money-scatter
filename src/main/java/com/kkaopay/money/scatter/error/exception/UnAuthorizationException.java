package com.kkaopay.money.scatter.error.exception;

import com.kkaopay.money.scatter.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UnAuthorizationException extends MoneyScatterException {

    public UnAuthorizationException() {
        super(ErrorCode.HANDLE_ACCESS_DENIED);
    }

    public UnAuthorizationException(final String message) {
        super(message, ErrorCode.HANDLE_ACCESS_DENIED);
    }

    public UnAuthorizationException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
