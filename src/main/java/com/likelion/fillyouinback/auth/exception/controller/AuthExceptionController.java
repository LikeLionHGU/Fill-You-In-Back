package com.likelion.fillyouinback.auth.exception.controller;

import com.likelion.fillyouinback.auth.exception.FailedToGoogleLoginException;
import com.likelion.fillyouinback.base.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionController {
    @ExceptionHandler(FailedToGoogleLoginException.class)
    public ResponseEntity<ExceptionResponse> handleFailedToGoogleLoginException(FailedToGoogleLoginException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
