package com.knits.enterprise.controller.company;

import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.service.company.BusinessUnitService;
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
public class BusinessUnitController {
    @Autowired
    private BusinessUnitService businessUnitService;

    @PostMapping(value = "/businessUnits", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<BusinessUnitDto> createNewBusinessUnit(@RequestBody BusinessUnitDto businessUnitDto) {
        log.debug("REST request to create BusinessUnit");
        return ResponseEntity
                .ok()
                .body(businessUnitService.saveNewBusinessUnit(businessUnitDto));
    }

    @PatchMapping(value = "/businessUnits/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<BusinessUnitDto> updateBusinessUnit(@PathVariable(value = "id") Long id, @RequestBody BusinessUnitDto businessUnitDto) {
        BusinessUnitDto businessUnitFound = businessUnitService.partialUpdate(businessUnitDto);
        return ResponseEntity
                .ok()
                .body(businessUnitFound);
    }

    @PutMapping(value = "/businessUnits/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<BusinessUnitDto> deactivateBusinessUnit(@PathVariable(value = "id") Long id, @RequestBody BusinessUnitDto businessUnitDto) {
        BusinessUnitDto businessUnitFound = businessUnitService.deactivateBusinessUnit(businessUnitDto);
        return ResponseEntity
                .ok()
                .body(businessUnitFound);
    }
}
