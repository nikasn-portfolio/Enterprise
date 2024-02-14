package com.knits.enterprise.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAnalyticsDto {

    private List<EmployeesCountByAgeGroupDto> employeesCountByAgeGroupDtos;
    private List<EmployeesCountByGenderDto> employeesCountByGenderDtos;

    private List<EmployeesCountByOfficeDto> employeesCountByOffices;

    private List<EmployeesCountByExperienceDto> employeesCountByExperienceDtos;
    private List<EmployeesCountByJobTitleDto> employeesCountByJobTitleDtos;
    private List<EmployeesCountByBusinessUnitDto> employeesCountByBusinessUnitDtos;
    private List<EmployeesCountInDepartmentDto> employeesCountInDepartmentDtos;

    private List<EmployeesTotalCountByYearDto> employeesTotalCountByYearDtos;

    private List<EmployeesHiredCountByYearDto> employeesHiredCountByYear;

    private List<EmployeesLeftCountByYearDto> employeesLeftCountByYearDtos;

    private EmployeesCountInJobTitleDto employeesCountInJobTitleDto;

    private EmployeesCountInBusinessUnitDto employeesCountInBusinessUnitDto;

    private EmployeesCountInDepartmentDto employeesCountInDepartmentDto;
}
