package com.kkaopay.money.scatter.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Common
    NO_REQUIRED_HEADER_INFO(HttpStatus.BAD_REQUEST, ErrorMessage.NO_REQUIRED_HEADER_INFO),
    NULL_VALUE(HttpStatus.BAD_REQUEST, ErrorMessage.IS_NULL_VALUE),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_INPUT_VALUE),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Invalid Input Value"),

    // 받기 API
    CANNOT_RECEIVE_BECAUSE_OWNER(HttpStatus.BAD_REQUEST, ErrorMessage.CANNOT_RECEIVE_BECAUSE_OWNER),
    REQUIRED_SAME_ROOM(HttpStatus.BAD_REQUEST, ErrorMessage.REQUIRED_SAME_ROOM),
    CANNOT_RECEIVE_BECAUSE_ALL_ALLOCATED(HttpStatus.BAD_REQUEST, ErrorMessage.CANNOT_RECEIVE_BECAUSE_ALL_ALLOCATED),
    USER_IS_ALREADY_ALLOCATED(HttpStatus.BAD_REQUEST, ErrorMessage.USER_IS_ALREADY_ALLOCATED),
    EXPIRED_SCATTER_MONEY(HttpStatus.BAD_REQUEST, ErrorMessage.EXPIRED_SCATTER_MONEY),

    // 조회 API
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, ErrorMessage.UN_AUTHORIZATION),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_EXIST_VALUE),
    EXPIRED_INQUIRY_PERIOD(HttpStatus.BAD_REQUEST, ErrorMessage.EXPIRED_INQUIRY_PERIOD);

    private HttpStatus status;
    private String message;

    ErrorCode(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return this.status.value();
    }
}
