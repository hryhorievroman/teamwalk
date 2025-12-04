package com.xcompany.teamwalk.controllerAdvice;

import com.xcompany.teamwalk.dto.ErrorResponse;
import com.xcompany.teamwalk.exception.ErrorCode;
import com.xcompany.teamwalk.exception.TeamAlreadyExistsException;
import com.xcompany.teamwalk.exception.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    ResponseEntity<ErrorResponse> handleTeamNotFoundException(TeamNotFoundException ex) {
        return buildErrorResponse(ErrorCode.TEAM_NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(TeamAlreadyExistsException.class)
    ResponseEntity<ErrorResponse> handleTeamAlreadyExistsException(TeamAlreadyExistsException ex) {
        return buildErrorResponse(ErrorCode.TEAM_ALREADY_EXISTS, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(ErrorCode.INVALID_INPUT, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return buildErrorResponse(ErrorCode.INTERNAL_ERROR, ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(ErrorCode errorCode, String message) {
        String bodyMessage = message != null ? message : errorCode.getDefaultMessage();
        ErrorResponse response = new ErrorResponse(errorCode.getCode(), bodyMessage, errorCode.getStatus().value());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }
}
