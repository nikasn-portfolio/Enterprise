package com.knits.enterprise.controller.common;

import com.knits.enterprise.dto.common.EmploymentContractDto;
import com.knits.enterprise.http.ResponseMessage;
import com.knits.enterprise.service.common.EmploymentContractService;
import com.knits.enterprise.validation.FileValidation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class EmploymentContractController {

    private EmploymentContractService employmentContractService;
    private FileValidation fileValidation;

    @PostMapping(value = "/employment-contracts")
    public ResponseEntity<ResponseMessage> createNewEmploymentContract(
            @RequestBody MultipartFile employmentContract) {
        log.debug("REST request to create EmploymentContract");
        String message;
        String extension = FilenameUtils.getExtension(
                employmentContract.getOriginalFilename());
        if (fileValidation.isSupportedExtension(extension)) {
            try {
                employmentContractService.saveNewEmploymentContract(employmentContract);
                message = "Employment Contract Uploaded successfully: " + employmentContract.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + employmentContract.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        } else {
            message = "Invalid file type: " + employmentContract.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping(value = {"/employment-contracts/{id}"}, produces = "application/json")
    public ResponseEntity<byte[]> getEmploymentContractById(@PathVariable (value="id") final Long id) {
        EmploymentContractDto employmentContractFound = employmentContractService.findEmploymentContractById(id);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + employmentContractFound.getFileName() + "\"")
                .body(employmentContractFound.getData());
    }

}
