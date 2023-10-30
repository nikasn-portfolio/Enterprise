package com.knits.enterprise.dto.common;

import lombok.Data;

@Data
public class EmployeesCountByAgeGroupDto {
    private String ageGroup;
    private Long employeesCount;

    public EmployeesCountByAgeGroupDto(String ageGroup, Long employeesCount) {
        this.ageGroup = ageGroup;
        this.employeesCount = employeesCount;
    }

    // Getter and setter methods
}
