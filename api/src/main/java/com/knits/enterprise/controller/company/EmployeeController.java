package com.knits.enterprise.controller.company;

import com.knits.enterprise.dto.analytics.EmployeeAnalyticsDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.search.EmployeeSearchDto;
import com.knits.enterprise.service.company.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employees", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
        log.debug("REST request to create Employee");
        return ResponseEntity
                .ok()
                .body(employeeService.saveNewEmployee(employeeDto));
    }

    @GetMapping(value = "/employees/{id}", produces = {"application/json"})
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(value = "id") final Long id) {
        log.debug("REST request to get Employee : {}", id);
        EmployeeDto employeeFound = employeeService.findEmployeeById(id);
        return ResponseEntity
                .ok()
                .body(employeeFound);

    }

    @PatchMapping(value = "/employees", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(value = "id") @RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeFound = employeeService.partialUpdate(employeeDto);
        return ResponseEntity
                .ok()
                .body(employeeFound);
    }

    @PutMapping(value = "/employees", produces = {"application/json"})
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable(value = "id") final Long id) {
        log.debug("REST request to delete Employee : {}", id);
        EmployeeDto employeeFound = employeeService.deleteEmployee(id);
        return ResponseEntity
                .ok()
                .body(employeeFound);
    }

    @GetMapping(value = "/employeeSearch", produces = {"application/json"})
    public ResponseEntity<PageImpl<EmployeeDto>> searchForEmployees(@RequestBody(required = false) EmployeeSearchDto searchDto){
        if(searchDto == null){
            searchDto = new EmployeeSearchDto();
        }
        PageImpl<EmployeeDto> foundedEmployeeDtos = employeeService.searchForEmployees(searchDto);
        return ResponseEntity
                .ok()
                .body(foundedEmployeeDtos);
    }

    @GetMapping(value = "/employeeExel", produces = {"application/json"})
    public ResponseEntity exelDocumentForEmployees(@RequestBody(required = false) EmployeeSearchDto searchDto){
        if(searchDto == null){
            searchDto = new EmployeeSearchDto();
        }
        ByteArrayOutputStream report = employeeService.makeExelOfEmployees(searchDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.xlsx");
        return ResponseEntity.ok().headers(headers).body(report.toByteArray());
    }

    @PostMapping(value = "/employeeExel")
    public ResponseEntity addEmployeesFromExcelFile(@RequestParam(value = "fileName") String fileName){
        if(fileName == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        employeeService.addEmployeeFromExelFile(fileName);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/addEmployeesToGroup")
    public ResponseEntity assignEmployeeToGroup(@RequestParam(value = "groupId") Long groupId,
                                              @RequestParam(value = "employeeIds") String employeeIds){
        if(groupId == null || employeeIds == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        employeeService.assignEmployeeToGroup(groupId, employeeIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/employeeAnalysis", produces = {"application/json"})
    public ResponseEntity assignEmployeeToGroup(){
        EmployeeAnalyticsDto employeeAnalyticsDto = employeeService.employeeAnalytics();
        return ResponseEntity.ok().body(employeeAnalyticsDto);
    }



}
