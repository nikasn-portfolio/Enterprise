package com.knits.enterprise.controller.company;

import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.search.BusinessUnitSearchDto;
import com.knits.enterprise.exception.handlers.ApiRequestValidationError;
import com.knits.enterprise.service.company.BusinessUnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
            @ApiResponse(responseCode = "400", description = "Requested body is invalid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiRequestValidationError.class))})
    })
    @PostMapping(value = "/business-unit-service/business-unit",produces = "application/json", consumes = "application/json")
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
            @ApiResponse(responseCode = "400", description = "Requested body is invalid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiRequestValidationError.class))}),
            @ApiResponse(responseCode = "404", description = "Business unit is not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiRequestValidationError.class))})
    })
    @PatchMapping(value= "/business-unit-service/business-unit", produces = "application/json", consumes = "application/json")
    public ResponseEntity<BusinessUnitDto> updateBusinessUnit(@RequestBody BusinessUnitDto businessUnitDto) {
        BusinessUnitDto businessUnitFound = businessUnitService.partialUpdate(businessUnitDto);
        return ResponseEntity
                .ok()
                .body(businessUnitFound);
    }

    @Operation(summary = "Deactivates business unit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Business is deactivated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessUnitDto.class))}),
            @ApiResponse(responseCode = "404", description = "Business unit is not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiRequestValidationError.class))})
    })
    @PutMapping(value = "/business-unit-service/business-unit/{id}", produces = "application/json")
    public ResponseEntity<BusinessUnitDto> deactivateBusinessUnit(@Parameter(description = "id of business unit to be deactivated") @PathVariable(value = "id") Long id) {
        businessUnitService.deactivateBusinessUnit(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Deletes business unit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Business is deactivated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessUnitDto.class))}),
            @ApiResponse(responseCode = "404", description = "Business unit is not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiRequestValidationError.class))})
    })
    @DeleteMapping(value = "/business-unit-service/business-unit/{id}")
    public ResponseEntity<BusinessUnitDto> deleteBusinessUnit(@Parameter(description = "id of business unit to be deleted") @PathVariable(value = "id") Long id) {
        businessUnitService.deleteBusinessUnit(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Gets business unit by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Business unit is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessUnitDto.class))}),
            @ApiResponse(responseCode = "404", description = "Business unit is not found", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiRequestValidationError.class))})
    })
    @GetMapping(value = "/business-unit-service/business-unit/{id}", produces = "application/json")
    public ResponseEntity<BusinessUnitDto> getBusinessUnitById(@Parameter(description = "id of business unit to be found") @PathVariable(value = "id") Long id) {
        return ResponseEntity
                .ok()
                .body(businessUnitService.findBusinessUnitById(id));
    }

    @Operation(summary = "Gets paginated business unit also can be filtered by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagination is successful",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BusinessUnitDto.class))})
    })
    @GetMapping(value = "/business-unit-service/business-unit", produces = "application/json")
    public PaginatedResponseDto<BusinessUnitDto> getPaginatedContent(@Parameter (description = "page number (optional)") @RequestParam(value = "page", required = false) Integer page,
                                                                     @Parameter (description = "size of content (optional)") @RequestParam(value = "limit", required = false) Integer limit,
                                                                     @Parameter (description = "sort parameters separated by comma (optional)") @RequestParam(value = "sort", required = false) String sort,
                                                                     @Parameter (description = "sort direction (optional)")@RequestParam(value = "dir", required = false) Sort.Direction dir,
                                                                     @Parameter (description = "name to be searched by (optional)")@RequestParam(value = "name", required = false) String name) {
        BusinessUnitSearchDto searchDto = new BusinessUnitSearchDto();
        searchDto.setFieldsIfNull(page, limit, sort, dir, name);
        PaginatedResponseDto<BusinessUnitDto> paginatedResponseDto = businessUnitService.search(searchDto);
        return paginatedResponseDto;
    }
}
