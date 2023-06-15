package com.example.classicmodels.controller.errorHandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import lombok.Getter;

// not used

public class ApiError {

    @Getter
    private HttpStatus status;
    @Getter
    private String message;
    @Getter
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
