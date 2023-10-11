package com.facundom.weWelcom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ReviewCreationException.class)
        public ResponseEntity<ApiResponse> handleReviewCreationException(ReviewCreationException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(ex.getMessage()));
        }

        @ExceptionHandler(ReviewNotFoundException.class)
        public ResponseEntity<ApiResponse> handleReviewNotFoundException(ReviewNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(ex.getMessage()));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Internal Server Error"));
        }
}
