package com.user.UserService.exception;

import com.user.UserService.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handlerResourceNotFound(ResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        APIResponse response = APIResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<APIResponse>(response,HttpStatus.NOT_FOUND);
    }
}
