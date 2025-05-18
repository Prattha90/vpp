package com.pratha.powerproject.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Pramosh Shrestha
 * @created 18/05/2025: 14:14
 */
@RestControllerAdvice
public class GlobalExceptionHandlerRestAdviceController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDTO> handleOnException(final Exception ex) {
        ex.printStackTrace();

        return new ResponseEntity<>(
                new ErrorResponseDTO()
                        .setDetails(ex.toString())
                        .setMessage(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // Handle DataIntegrityViolationException, which is generally thrown
    // when a duplicate key error occurs.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

        String message = ex.getMessage();
        // Example: Check if message contains a duplicate key violation keyword
        if (message != null && message.contains("duplicate key value violates unique constraint")) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO()
                    .setDetails("Duplicate key error" + ex)
                    .setMessage("The provided value already exists. Please provide a unique value.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorResponseDTO()
                                .setDetails("Internal Server Error" + ex)
                                .setMessage(ex.getMessage())
                );
    }
}
