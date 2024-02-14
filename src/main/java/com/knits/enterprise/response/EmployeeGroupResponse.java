package com.knits.enterprise.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeGroupResponse {
    private Long id;
    private int code;
    private String message;
}
