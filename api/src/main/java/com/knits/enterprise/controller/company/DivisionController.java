package com.knits.enterprise.controller.company;

import com.knits.enterprise.dto.company.DivisionDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.service.company.DivisionService;
import com.knits.enterprise.service.company.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/division/{id}", produces = {"application/json"})
    public ResponseEntity<DivisionDto> getDivisionById(@PathVariable(value = "id") final Long id) {
        log.debug("REST request to get Division: {}", id);
        DivisionDto divisionFound = divisionService.findDivisionById(id);
        return ResponseEntity.ok().body(divisionFound);
    }

    // Using PUT because I am not sure what information might come from outside.
    @PutMapping(value = "/division", produces = {"application/json"})
    public ResponseEntity<DivisionDto> updateDivision(@RequestBody final Long employeeId, DivisionDto divisionDto) throws IllegalAccessException {

        log.debug("REST request to update Division: {}", divisionDto.getId());

        EmployeeDto employee = employeeService.findEmployeeById(employeeId);

        // Task said "COM-178 HRAdmin can edit existing Division" and I was not sure how to validate, that user is
        // actually a HRAdmin, properly, so I simply check if user is from HR department.

        if (employee.getDepartment().getId() == 6) {
            DivisionDto updatedDivision = divisionService.updateDivision(divisionDto);
            return ResponseEntity.ok().body(updatedDivision);
        } else {
            throw new IllegalAccessException("Employee not eligible to update division");
        }
    }

    @PutMapping(value = "/division/{id}/disable")
    public ResponseEntity disableDivision(@PathVariable(value = "id") Long divisionId) throws Exception {
        divisionService.disableDivision(divisionId);
        return ResponseEntity.ok().body("Division #" + divisionId + " disabled");
    }

    @PostMapping(value = "/division")
    public ResponseEntity<DivisionDto> createNewDivision(@RequestBody Long userId, String name, String description) {
        DivisionDto newDivisionDto = divisionService.createNewDivision(userId, name, description);
        return ResponseEntity.ok().body(newDivisionDto);
    }
}
