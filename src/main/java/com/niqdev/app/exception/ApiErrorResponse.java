package com.niqdev.app.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiErrorResponse {
    private final int status;
    private final String error;
    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
