package com.knits.enterprise.dto.analytics;

import com.knits.enterprise.dto.company.BusinessUnitDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesCountByBusinessUnitDto {
    private BusinessUnitDto businessUnitName;
    private Long employeesCount;
}
