package com.knits.enterprise.controller.company;

import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.DivisionDto;
import com.knits.enterprise.dto.search.DivisionSearchDto;
import com.knits.enterprise.model.company.Division;
import com.knits.enterprise.service.company.DivisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@AllArgsConstructor
@Tag(name = "Divisions", description = "Division crud operations")
@RequestMapping("/api")
@Slf4j
public class DivisionController {

   @Autowired
   private DivisionService divisionService;

    @ApiResponses (value = {
            @ApiResponse(responseCode = "200", description = "Division created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Division not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
   @PostMapping(value = "/divisions", produces = {"application/json"}, consumes = {"application/json"})
   @Operation(summary = "Create a new Division", description = "Send json containing necessary fields")
   public ResponseEntity<DivisionDto> createNewDivision(
           @Parameter(description = "Division object") @RequestBody @Valid DivisionDto divisionDto) {
       log.debug("REST request to create Division");
       return ResponseEntity
               .ok()
               .body(divisionService.saveNewDivision(divisionDto));
   }


    @ApiResponses (value = {
            @ApiResponse(responseCode = "200", description = "Retrieve division successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Division not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
   @Operation(summary = "Get Division by id", description = "Input id")
   @GetMapping(value = {"/divisions/{id}"}, produces = "application/json")
   public ResponseEntity<DivisionDto> getDivisionById(
           @Parameter(description = "id", example = "1") @PathVariable (value="id") final Long id) {
       log.debug("REST request to get Division : {}", id);
       DivisionDto divisionFound = divisionService.findDivisionById(id);
       return ResponseEntity
               .ok()
               .body(divisionFound);


   }


    @ApiResponses (value = {
            @ApiResponse(responseCode = "200", description = "Update operation is successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Division not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
   @PatchMapping(value = "/divisions", produces = {"application/json"}, consumes = {"application/json"})
   @Operation(summary = "Update Division", description = "Input id to update particular division")
   public ResponseEntity<DivisionDto> updateDivision(
           @Parameter(description = "id", example = "1")@PathVariable (value = "id") Long id,
           @Parameter(description = "Division object")@RequestBody @Valid DivisionDto divisionDto) {
       DivisionDto divisionFound = divisionService.partialUpdate(divisionDto);
       return ResponseEntity
               .ok()
               .body(divisionFound);
   }


    @ApiResponses (value = {
            @ApiResponse(responseCode = "200", description = "Delete operation is successful."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Division not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
   @Operation(summary = "Delete Division", description = "Input id to delete particular division")
   @DeleteMapping(value = "/divisions", produces = {"application/json"})
   public ResponseEntity<DivisionDto> deleteDivision(
           @Parameter(description = "id", example = "1") @PathVariable (value="id") final Long id) {
       log.debug("REST request to delete Division : {}", id);
       DivisionDto divisionFound = divisionService.deleteDivision(id);
       return ResponseEntity
               .ok()
               .body(divisionFound);
   }

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Retrieve the paginated divisions successfully."),
        @ApiResponse(responseCode = "400", description = "Bad request."),
        @ApiResponse(responseCode = "404", description = "Division not found."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @Operation(summary = "Get paginated divisions", description = "Paginated list of divisions")
    @GetMapping(value="/divisions", produces = {"application/json"})
    public ResponseEntity<PaginatedResponseDto<DivisionDto>> getAllDivisions(
            DivisionSearchDto<Division> searchDto
            ) {

        PaginatedResponseDto<DivisionDto> divisions = divisionService.findAllDivision(searchDto);
        return ResponseEntity
                .ok()
                .body(divisions);
    }

}
