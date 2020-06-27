package com.kkaopay.money.scatter.error.advice;

import com.kkaopay.money.scatter.error.ErrorCode;
import com.kkaopay.money.scatter.error.ErrorResponse;
import com.kkaopay.money.scatter.error.exception.InvalidValueException;
import com.kkaopay.money.scatter.error.exception.NoRequiredHeaderException;
import com.kkaopay.money.scatter.error.exception.NotExistValueException;
import com.kkaopay.money.scatter.error.exception.UnAuthorizationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** * 필수 헤더가 없는 경우 발생 */
    @ExceptionHandler(NoRequiredHeaderException.class)
    protected ResponseEntity<ErrorResponse> handleNoRequiredHeaderException(NoRequiredHeaderException e) {
        log.error("handleNoRequiredHeaderException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NO_REQUIRED_HEADER_INFO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.NO_REQUIRED_HEADER_INFO.getCode()));
    }

    /** * 권한 없는 경우 발생 */
    @ExceptionHandler(UnAuthorizationException.class)
    protected ResponseEntity<ErrorResponse> handelUnAuthorizationException(UnAuthorizationException e) {
        log.error("handelUnAuthorizationException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    /** * 값이 유효하지 않은 경우 발생 */
    @ExceptionHandler(InvalidValueException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidValueException(InvalidValueException e) {
        log.error("handleInvalidValueException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.INVALID_INPUT_VALUE.getCode()));
    }

    /** * 해당 데이터가 존재하지 않은 경우 발생 */
    @ExceptionHandler(NotExistValueException.class)
    protected ResponseEntity<ErrorResponse> handleNotExistValueException(NotExistValueException e) {
        log.error("handleNotExistValueException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    /** * NullPointerException 경우 발생 */
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NULL_VALUE);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.HANDLE_ACCESS_DENIED.getCode()));
    }

    /** * 지원하지 않은 HTTP method 호출 할 경우 발생 */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /** * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생 */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.HANDLE_ACCESS_DENIED.getCode()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
