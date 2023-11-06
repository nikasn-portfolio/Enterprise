package com.knits.enterprise.analyticsModel;

import com.knits.enterprise.model.company.JobTitle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesCountByJobTitle {
    private JobTitle jobTitle;
    private Long employeesCount;
}
