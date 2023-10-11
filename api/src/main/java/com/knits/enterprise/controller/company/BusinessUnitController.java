package com.knits.enterprise.controller.company;

import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.search.GenericSearchDto;
import com.knits.enterprise.model.company.BusinessUnit;
import com.knits.enterprise.service.company.BusinessUnitService;
import com.knits.enterprise.service.company.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Creates business unit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Business unit is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessUnitDto.class))}),
            @ApiResponse(responseCode = "400", description = "Requested body is invalid")
    })
    @PostMapping(value = "/businessUnits", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<BusinessUnitDto> createNewBusinessUnit(@Valid @RequestBody BusinessUnitDto businessUnitDto) {
        log.debug("REST request to create BusinessUnit");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(businessUnitService.saveNewBusinessUnit(businessUnitDto));
    }

    @Operation(summary = "Updates business unit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Business unit is updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessUnitDto.class))}),
            @ApiResponse(responseCode = "400", description = "Requested body is invalid"),
            @ApiResponse(responseCode = "404", description = "Business unit is not found")
    })
    @PatchMapping(value = "/businessUnits/{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<BusinessUnitDto> updateBusinessUnit(@Parameter(description = "id of business unit to be updated") @PathVariable(value = "id") Long id,
                                                              @RequestBody BusinessUnitDto businessUnitDto) {
        BusinessUnitDto businessUnitFound = businessUnitService.partialUpdate(id, businessUnitDto);
        return ResponseEntity
                .ok()
                .body(businessUnitFound);
    }

    @Operation(summary = "Deactivates business unit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Business is deactivated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessUnitDto.class))}),
            @ApiResponse(responseCode = "400", description = "Requested body is invalid"),
            @ApiResponse(responseCode = "404", description = "Business unit is not found")
    })
    @PutMapping(value = "/businessUnits/{id}", produces = {"application/json"})
    public ResponseEntity<BusinessUnitDto> deactivateBusinessUnit(@Parameter(description = "id of business unit to be deactivated") @PathVariable(value = "id") Long id) {
        BusinessUnitDto businessUnitFound = businessUnitService.deactivateBusinessUnit(id);
        return ResponseEntity
                .ok()
                .body(businessUnitFound);
    }

    @Operation(summary = "Gets paginated business unit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagination is successful",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessUnitDto.class))}),
            @ApiResponse(responseCode = "400", description = "Requested body is invalid"),
            @ApiResponse(responseCode = "404", description = "Business unit is not found")
    })
    @GetMapping(value = "/businessUnits", produces = {"application/json"}, consumes = {"application/json"})
    public PaginatedResponseDto<BusinessUnitDto> getPaginatedContent(@Parameter(description = "object describes parameters for pagination") @RequestBody GenericSearchDto<BusinessUnit> searchDto) {
        PaginatedResponseDto<BusinessUnitDto> paginatedResponseDto = businessUnitService.listAll(searchDto);
        return paginatedResponseDto;
    }
}
