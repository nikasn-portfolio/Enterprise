package com.knits.enterprise.model.analytics;

import com.knits.enterprise.model.company.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesCountByDepartment {
    private Department department;
    private Long employeesCount;
}
