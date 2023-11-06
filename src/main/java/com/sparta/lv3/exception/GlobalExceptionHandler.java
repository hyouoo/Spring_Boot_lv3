package com.sparta.lv3.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage authenticationException(RuntimeException ex) {
        return new ErrorMessage(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> validationException(MethodArgumentNotValidException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> illegalArgumentException(IllegalArgumentException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorityViolationException.class)
    public ResponseEntity<ErrorMessage> authorityException(AuthorityViolationException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> nullPointerException(NullPointerException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
