package com.knits.enterprise.exception.handlers;

import com.knits.enterprise.exception.UserException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiRequestValidationError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ApiRequestValidationError(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiRequestValidationError> handleNotFoundErrors(UserException ex){
        List<String> errors = List.of(ex.getMessage());
        return new ResponseEntity<>(new ApiRequestValidationError(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ApiRequestValidationError> handlePropertyReferenceErrors(PropertyReferenceException ex){
        List<String> errors = List.of(ex.getMessage());
        return new ResponseEntity<>(new ApiRequestValidationError(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


}
