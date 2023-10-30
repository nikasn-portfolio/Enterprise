package com.knits.enterprise.analyticsModel;

import com.knits.enterprise.model.company.BusinessUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesCountByBusinessUnit {
    private BusinessUnit businessUnit;
    private Long employeesCount;
}
