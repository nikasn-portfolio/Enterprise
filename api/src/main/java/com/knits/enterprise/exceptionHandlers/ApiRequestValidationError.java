package com.knits.enterprise.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiRequestValidationError {
    List<String> errorMessages;
}