package com.xcompany.teamwalk.controllerAdvice;

import com.xcompany.teamwalk.exception.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TeamNotFoundException.class)
    ResponseEntity<Object> handleTeamNotFoundException(TeamNotFoundException ex) {
        String body = ex.getMessage();
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
