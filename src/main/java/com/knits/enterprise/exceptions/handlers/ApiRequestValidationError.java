package com.knits.enterprise.exceptions.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiRequestValidationError {
    List<String> errorMessages;
}
