package com.knits.enterprise.dto.company;

import com.knits.enterprise.dto.security.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDto {

    private Long id;
    private UserDto createdBy;
    private EmployeeDto employee;
    private Boolean active;
    private LocalDateTime createdAt;
}
