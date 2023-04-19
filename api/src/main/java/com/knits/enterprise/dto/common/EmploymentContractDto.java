package com.knits.enterprise.dto.common;

import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.model.security.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentContractDto {
    private Long id;
    private String fileName;
    private String fileType;
    private byte[] data;
    private LocalDateTime created;
    private User created_by;
    private Employee employee;
}
