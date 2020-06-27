package com.kkaopay.money.scatter.error.advice;

import com.kkaopay.money.scatter.error.ErrorCode;
import com.kkaopay.money.scatter.error.ErrorResponse;
import com.kkaopay.money.scatter.error.exception.UnAuthorizationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = UnAuthorizationException.class)
    public ResponseEntity<?> handleBaseException(HttpServletRequest request, UnAuthorizationException e) {
        log.error(String.valueOf(request.getMethod()));
        log.error(String.valueOf(request.getRequestURL()));
        return new ResponseEntity<>("GlobalExceptionHandler.handleException" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnAuthorizationException.class)
    protected ResponseEntity<ErrorResponse> handelUnAuthorizationException(UnAuthorizationException e) {
        log.error("handelUnAuthorizationException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);

        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
