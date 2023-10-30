package com.knits.enterprise.dto.common;

import com.knits.enterprise.dto.company.BusinessUnitDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesCountInBusinessUnitDto {
    private BusinessUnitDto businessUnit;
    private Long employeesCount;
}
