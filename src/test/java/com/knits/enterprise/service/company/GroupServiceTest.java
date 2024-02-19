package com.knits.enterprise.service.company;

import com.knits.enterprise.dto.company.GroupDto;
import com.knits.enterprise.dto.response.ReportResponse;
import com.knits.enterprise.exception.UserException;
import com.knits.enterprise.mapper.company.GroupMapper;
import com.knits.enterprise.mapper.company.GroupMapperImpl;
import com.knits.enterprise.model.EmployeeMock;
import com.knits.enterprise.model.GroupMock;
import com.knits.enterprise.repository.company.EmployeeRepository;
import com.knits.enterprise.repository.company.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {
    @Mock
    private GroupRepository groupRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private GroupMapper groupMapper = new GroupMapperImpl();
    @InjectMocks
    private GroupService groupService;

    @Test
    public void testAddEmployeeToExistingGroup() {
        long groupId = 1L;
        Set<Long> employeeIds = Set.of(1L, 2L, 3L);
        Mockito.when(groupRepository.findByIdWithEmployees(groupId)).thenReturn(Optional.of(GroupMock.shallowGroupMock(groupId)));
        Mockito.when(employeeRepository.findAllById(employeeIds)).thenReturn(Optional.of(EmployeeMock.shallowSetOfEmployees(3)));
        ReportResponse<GroupDto> groupDtoReportResponse = groupService.addEmployeeToGroup(groupId, employeeIds);
        Mockito.verify(groupRepository, Mockito.times(1)).findByIdWithEmployees(groupId);
        Mockito.verify(employeeRepository, Mockito.times(1)).findAllById(employeeIds);
        assertEquals(3, groupDtoReportResponse.getReports().size());
        groupDtoReportResponse.getReports().forEach((k, v) -> {
            assertEquals(v.getCode(), 1024);
        });
    }

    @Test
    public void testAddEmployeeToNonExistingGroup() {
        assertThrows(UserException.class, () -> {
            long groupId = 1L;
            Set<Long> employeeIds = Set.of(1L, 2L, 3L);
            Mockito.when(groupRepository.findByIdWithEmployees(groupId)).thenReturn(Optional.empty());
            groupService.addEmployeeToGroup(groupId, employeeIds);
        });
    }

    @Test
    public void testAddNonExistingEmployeeToGroup() {
        assertThrows(UserException.class, () -> {
            long groupId = 1L;
            Set<Long> employeeIds = Set.of(1L, 2L, 3L);
            Mockito.when(groupRepository.findByIdWithEmployees(groupId)).thenReturn(Optional.of(GroupMock.shallowGroupMock(groupId)));
            Mockito.when(employeeRepository.findAllById(employeeIds)).thenReturn(Optional.of(Set.of()));
            groupService.addEmployeeToGroup(groupId, employeeIds);
        });
    }
}
