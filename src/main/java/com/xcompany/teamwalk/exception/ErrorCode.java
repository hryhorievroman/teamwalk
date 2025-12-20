package com.xcompany.teamwalk.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    TEAM_NOT_FOUND("The team is not found", HttpStatus.NOT_FOUND),
    TEAM_ALREADY_EXISTS("The team already exists", HttpStatus.CONFLICT),
    INVALID_INPUT("Invalid input provided", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String defaultMessage;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.defaultMessage = message;
        this.status = status;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
