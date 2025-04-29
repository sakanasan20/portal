package com.niqdev.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiErrorException.class)
    public ModelAndView handleApiErrorException(ApiErrorException ex) {
        ModelAndView modelAndView = new ModelAndView("api-result");
        ApiErrorResponse error = ex.getApiErrorResponse();
        modelAndView.setStatus(HttpStatus.valueOf(error.getStatus()));
        modelAndView.addObject("result", error);
        return modelAndView;
    }
}
