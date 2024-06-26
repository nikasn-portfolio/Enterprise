package com.knits.enterprise.dto.analytics;

import com.knits.enterprise.dto.company.JobTitleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesCountByJobTitleDto {
    private JobTitleDto jobTitle;
    private Long employeesCount;
}
