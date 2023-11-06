package com.knits.enterprise.dto.analytics;

import com.knits.enterprise.dto.company.DepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesCountInDepartmentDto {
    private DepartmentDto department;
    private Long employeesCount;
}
