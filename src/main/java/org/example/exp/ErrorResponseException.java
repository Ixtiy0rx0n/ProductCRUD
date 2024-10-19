package org.example.exp;

import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorResponseException extends RuntimeException{
    private final List<FieldError> fieldErrors;

    public ErrorResponseException(String message, List<FieldError> fieldErrors) {
        super(message);
        this.fieldErrors = fieldErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
