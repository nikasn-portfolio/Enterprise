package com.knits.enterprise.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesHiredCountByYearDto {
    private String year;
    private Long employeesCount;
}
