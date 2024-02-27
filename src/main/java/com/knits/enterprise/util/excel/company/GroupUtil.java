package com.knits.enterprise.util.excel.company;

import com.knits.enterprise.dto.company.GroupDto;
import com.knits.enterprise.dto.response.ReportDto;
import com.knits.enterprise.dto.response.ReportResponse;

import java.util.Set;

public class GroupUtil {
    public static ReportResponse<GroupDto> createReport(ReportResponse<GroupDto> reportResponse, Set<Long> employeeIdsInGroup, Set<Long> employeeIdsToAdd, Set<Long> employeeIds) {
        employeeIds.stream().forEach(employeeId -> {
            if (employeeIdsInGroup.contains(employeeId)) {
                reportResponse.getReports().put(employeeId, ReportDto.builder().id(employeeId).code(Constants.EMPLOYEE_ALREADY_IN_GROUP_CODE).message(Constants.EMPLOYEE_ALREADY_IN_GROUP_MESSAGE).build());
            } else if (!employeeIdsToAdd.contains(employeeId)) {
                reportResponse.getReports().put(employeeId, ReportDto.builder().id(employeeId).code(Constants.EMPLOYEE_NOT_FOUND_CODE).message(Constants.EMPLOYEE_NOT_FOUND_MESSAGE).build());
            } else {
                reportResponse.getReports().put(employeeId, ReportDto.builder().id(employeeId).code(Constants.EMPLOYEE_ADDED_TO_GROUP_CODE).message(Constants.EMPLOYEE_ADDED_TO_GROUP_MESSAGE).build());
            }
        });
        return reportResponse;
    }
    private static class Constants {
        public static final String EMPLOYEE_ALREADY_IN_GROUP_MESSAGE = "Employee already in group";
        public static final String EMPLOYEE_NOT_FOUND_MESSAGE = "Employee not found";
        public static final String EMPLOYEE_ADDED_TO_GROUP_MESSAGE = "Employee added to group";
        public static final Integer EMPLOYEE_ALREADY_IN_GROUP_CODE = -100;
        public static final Integer EMPLOYEE_NOT_FOUND_CODE = -200;
        public static final Integer EMPLOYEE_ADDED_TO_GROUP_CODE = 1024;
    }
}
