package com.niqdev.app.exception;

public class ApiErrorException extends RuntimeException {

	private static final long serialVersionUID = 5325416941313808789L;
	
	private final ApiErrorResponse apiErrorResponse;

    public ApiErrorException(ApiErrorResponse apiErrorResponse) {
        super(apiErrorResponse.getMessage());
        this.apiErrorResponse = apiErrorResponse;
    }

    public ApiErrorResponse getApiErrorResponse() {
        return apiErrorResponse;
    }
}
