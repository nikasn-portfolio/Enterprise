package com.knits.enterprise.controller.company;

import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.search.GenericSearchDto;
import com.knits.enterprise.model.company.BusinessUnit;
import com.knits.enterprise.service.company.BusinessUnitService;
import com.knits.enterprise.service.company.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class BusinessUnitController {
    @Autowired
    private BusinessUnitService businessUnitService;

    @PostMapping(value = "/businessUnits", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<BusinessUnitDto> createNewBusinessUnit(@Valid @RequestBody BusinessUnitDto businessUnitDto) {
        log.debug("REST request to create BusinessUnit");
        return ResponseEntity
                .ok()
                .body(businessUnitService.saveNewBusinessUnit(businessUnitDto));
    }

    @PatchMapping(value = "/businessUnits/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<BusinessUnitDto> updateBusinessUnit(@PathVariable(value = "id") Long id, @RequestBody BusinessUnitDto businessUnitDto) {
        BusinessUnitDto businessUnitFound = businessUnitService.partialUpdate(id,businessUnitDto);
        return ResponseEntity
                .ok()
                .body(businessUnitFound);
    }

    @PutMapping(value = "/businessUnits/{id}", produces = {"application/json"})
    public ResponseEntity<BusinessUnitDto> deactivateBusinessUnit(@PathVariable(value = "id") Long id) {
        BusinessUnitDto businessUnitFound = businessUnitService.deactivateBusinessUnit(id);
        return ResponseEntity
                .ok()
                .body(businessUnitFound);
    }

    @GetMapping(value = "/businessUnits", produces = {"application/json"}, consumes = {"application/json"})
    public PaginatedResponseDto<BusinessUnitDto> getPaginatedContent(@RequestBody GenericSearchDto<BusinessUnit> searchDto){
        PaginatedResponseDto<BusinessUnitDto> paginatedResponseDto = businessUnitService.listAll(searchDto);
        return paginatedResponseDto;
    }
}
