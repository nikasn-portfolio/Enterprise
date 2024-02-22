package com.knits.enterprise.service.company;

import com.knits.enterprise.dto.company.GroupDto;
import com.knits.enterprise.dto.response.ReportResponse;
import com.knits.enterprise.exception.UserException;
import com.knits.enterprise.mapper.company.GroupMapper;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.model.company.Group;
import com.knits.enterprise.repository.company.EmployeeRepository;
import com.knits.enterprise.repository.company.GroupRepository;
import com.knits.enterprise.util.excel.company.GroupUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class GroupService {
    private final GroupRepository groupRepository;

    private final EmployeeRepository employeeRepository;

    private final GroupMapper groupMapper;

    public ReportResponse<GroupDto> addEmployeeToGroup(Long groupId, Set<Long> employeeIds){
        Group foundedGroup = groupRepository
                .findByIdWithEmployees(groupId).orElseThrow(() -> new UserException("Group not found with id: " + groupId));
        Set<Employee> employeesFromDb = employeeRepository.findAllById(employeeIds).get();
        if (employeesFromDb.isEmpty()) {
            throw new UserException("None of the Employee ids (%s) is existing".formatted(employeeIds.toString()));
        }

        Set<Long> employeeIdsInGroup = foundedGroup.getEmployees().stream().map(Employee::getId).collect(Collectors.toSet());
        Set<Long> employeeIdsToAdd = employeesFromDb.stream().map(Employee::getId).collect(Collectors.toSet());
        ReportResponse<GroupDto> reportResponse = ReportResponse.<GroupDto>builder()
                .parent(groupMapper.toDto(foundedGroup))
                .reports(new HashMap<>())
                .build();
        return GroupUtil.createReport(reportResponse, employeeIdsInGroup, employeeIdsToAdd, employeeIds);
    }

}
