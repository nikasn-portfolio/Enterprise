package com.knits.enterprise.controller.company;


import com.knits.enterprise.dto.company.GroupDto;
import com.knits.enterprise.dto.response.ReportResponse;
import com.knits.enterprise.service.company.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/group-service/groups")
@Slf4j
public class GroupController {
    private final GroupService groupService;
    @PatchMapping(value = "/{id}/employees", produces = {"application/json"})
    public ResponseEntity<ReportResponse<GroupDto>> addEmployeesToGroup(@PathVariable Long id, @RequestParam Set<Long> employeeIds) {
        log.debug("REST request to add list of Employees to Group :{}", id);
        return ResponseEntity.ok().body(groupService.addEmployeeToGroup(id, employeeIds));
    }
}
