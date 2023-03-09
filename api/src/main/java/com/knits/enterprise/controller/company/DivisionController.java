package com.knits.enterprise.controller.company;


import com.knits.enterprise.dto.company.DivisionDto;
import com.knits.enterprise.service.company.DivisionService;
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


   @PostMapping(value = "/divisions", produces = {"application/json"}, consumes = {"application/json"})
   public ResponseEntity<DivisionDto> createNewDivision(@RequestBody DivisionDto divisionDto) {
       log.debug("REST request to create Division");
       return ResponseEntity
               .ok()
               .body(divisionService.saveNewDivision(divisionDto));
   }


   @GetMapping(value = {"/divisions/{id}"}, produces = "application/json")
   public ResponseEntity<DivisionDto> getDivisionById(@PathVariable (value="id") final Long id) {
       log.debug("REST request to get Division : {}", id);
       DivisionDto divisionFound = divisionService.findDivisionById(id);
       return ResponseEntity
               .ok()
               .body(divisionFound);


   }


   @PatchMapping(value = "/divisions", produces = {"application/json"}, consumes = {"application/json"})
   public ResponseEntity<DivisionDto> updateDivision(@PathVariable (value = "id") @RequestBody DivisionDto divisionDto) {
       DivisionDto divisionFound = divisionService.partialUpdate(divisionDto);
       return ResponseEntity
               .ok()
               .body(divisionFound);
   }


   @DeleteMapping(value = "/divisions", produces = {"application/json"}) // @PutMapping?
   public ResponseEntity<DivisionDto> deleteDivision(@PathVariable (value="id") final Long id) {
       log.debug("REST request to delete Division : {}", id);
       DivisionDto divisionFound = divisionService.deleteDivision(id);
       return ResponseEntity
               .ok()
               .body(divisionFound);
   }
}
