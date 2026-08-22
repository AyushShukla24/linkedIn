package com.examplelinkeInProject.postsService.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
