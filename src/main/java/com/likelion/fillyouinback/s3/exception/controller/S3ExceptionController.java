package com.likelion.fillyouinback.s3.exception.controller;

import com.likelion.fillyouinback.base.response.ExceptionResponse;
import com.likelion.fillyouinback.s3.exception.ImageSizeLimitException;
import com.likelion.fillyouinback.s3.exception.NotImageException;
import com.likelion.fillyouinback.s3.exception.S3ImageUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class S3ExceptionController {

    @ExceptionHandler(NotImageException.class)
    public ResponseEntity<ExceptionResponse> handleNotImageException(NotImageException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ImageSizeLimitException.class)
    public ResponseEntity<ExceptionResponse> handleImageSizeLimitException(ImageSizeLimitException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(S3ImageUploadException.class)
    public ResponseEntity<ExceptionResponse> handleS3ImageUploadException(S3ImageUploadException e) {
        ExceptionResponse response = ExceptionResponse.builder()
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
