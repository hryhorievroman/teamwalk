package com.xcompany.teamwalk.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    TEAM_NOT_FOUND("TEAM_NOT_FOUND", "The team is not found", HttpStatus.NOT_FOUND),
    TEAM_ALREADY_EXISTS("TEAM_ALREADY_EXISTS", "The team is already exists", HttpStatus.CONFLICT),
    INVALID_INPUT("INVALID_INPUT", "Invalid input provided", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("INTERNAL_ERROR", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.defaultMessage = message;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
