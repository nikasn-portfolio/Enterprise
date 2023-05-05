package com.knits.enterprise.controller.common;

import com.knits.enterprise.dto.common.EmploymentContractDto;
import com.knits.enterprise.dto.search.EmploymentContractSearchDto;
import com.knits.enterprise.http.ResponseMessage;
import com.knits.enterprise.model.common.EmploymentContract;
import com.knits.enterprise.service.common.EmploymentContractService;
import com.knits.enterprise.validation.FileValidation;
import com.knits.enterprise.zip.ZipEntryHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class EmploymentContractController {

    private EmploymentContractService employmentContractService;
    private FileValidation fileValidation;
    private ZipEntryHelper zipEntryHelper;

    // ALLOWED FILE TYPES; PDF, DOCX AND ZIP
    @PostMapping(value = "/employment/contracts")
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
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message)); // 1
            }
        } else {
            message = "Invalid file type: " + employmentContract.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message)); // 1
        }
    }

    // DOWNLOAD MULTIPLE FILES or A SINGLE FILE IN ZIP
    // Example links:
    // localhost:8080/api/employment/contracts/zip?fileName=Employment-Contract-henri.pdf
    // localhost:8080/api/employment/contracts/zip
    @GetMapping(value = "employees/search/contracts/zip", produces = {"application/zip"})
    public ResponseEntity<byte[]> getAllEmploymentContractsZip(EmploymentContractSearchDto<EmploymentContract> searchDto) throws IOException {
        log.debug("REST request to search and get EmploymentContract");
        List<EmploymentContractDto> employmentContracts = employmentContractService.findAllEmploymentContracts(searchDto);
        return ResponseEntity.ok()
                        .body(ZipEntryHelper.generateZip(employmentContracts));
    }

    // api/employees/contracts/all @Return a list of contract ids
    @GetMapping(value = "employees/contracts/all", produces = {"application/json"})
    public ResponseEntity<List<Long>> getAllEmploymentContractIds() {
        log.debug("REST request to get all EmploymentContracts ids");
        return ResponseEntity.ok()
                .body(employmentContractService.findAllEmploymentContractsIds());
    }

    // api/employees/contracts/ids=1,2,3
    // Returns all documents fetched by ids in a .zip file.
    // For every id that is not found, a text file should be generated (and added to zip) with missing id as filename and a default text.
    @GetMapping(value = "employees/contracts/{id}", produces = {"application/zip"})
    public ResponseEntity<byte[]> getEmploymentContractsByIdAndGenerateZip(@PathVariable(value = "id") final List<Long> ids) throws IOException {
        log.debug("REST request to get EmploymentContracts by ids");
        return ResponseEntity.ok()
                .body(zipEntryHelper.generateZipFromIds(ids));
    }
}
