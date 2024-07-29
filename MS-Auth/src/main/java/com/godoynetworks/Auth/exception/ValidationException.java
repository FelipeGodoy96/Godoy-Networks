package com.godoynetworks.Auth.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<String> errors;

    private int status;

    public ValidationException(List<String> errors, int status) {
        super("Validation failed");
        this.errors = errors;
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getStatus() {
        return status;
    }

}
