package com.knits.enterprise.exceptions;

import com.knits.enterprise.exceptionHandlers.CustomError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new CustomError(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<CustomError> handleNotFoundErrors(UserException ex){
        List<String> errors = List.of(ex.getMessage());
        return new ResponseEntity<>(new CustomError(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<CustomError> handlePropertyReferenceErrors(PropertyReferenceException ex){
        List<String> errors = List.of(ex.getMessage());
        return new ResponseEntity<>(new CustomError(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


}
