package com.knits.enterprise.controller.common;

import com.knits.enterprise.dto.common.EmploymentContractDto;
import com.knits.enterprise.dto.search.EmploymentContractSearchDto;
import com.knits.enterprise.http.ResponseMessage;
import com.knits.enterprise.mapper.common.EmploymentContractMapper;
import com.knits.enterprise.model.common.EmploymentContract;
import com.knits.enterprise.service.common.EmploymentContractService;
import com.knits.enterprise.validation.FileValidation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class EmploymentContractController {

    private EmploymentContractService employmentContractService;
    private FileValidation fileValidation;

    // ALLOWED FILE TYPES; PDF, DOCX AND ZIP
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

    // DOWNLOAD MULTIPLE FILES or A SINGLE FILE IN ZIP
    // Example links:
    // localhost:8080/api/employment-contracts-zip?fileName=Employment-Contract-henri.pdf
    // localhost:8080/api/employment-contracts-zip
    @GetMapping(value = "employment-contracts-zip", produces="application/zip")
    public byte[] zipDownload(EmploymentContractSearchDto<EmploymentContract> searchDto) throws IOException {

        // List of employment contracts
        List<EmploymentContractDto> employmentContracts = employmentContractService.findAllEmploymentContracts(searchDto);

        // Map of file names and byte arrays
        Map<String, byte[]> files = new HashMap<>();

        for (EmploymentContractDto employmentContract : employmentContracts) {
            String filename = employmentContract.getFileName();
            byte[] bytes = employmentContract.getData();
            files.put(filename, bytes);
        }

        // Zip files from the MAP
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        for (Map.Entry<String, byte[]> contracts : files.entrySet()) {
            ZipEntry entry = new ZipEntry(contracts.getKey());
            entry.setSize(contracts.getValue().length);
            zos.putNextEntry(entry);
            zos.write(contracts.getValue());
        }
        zos.closeEntry();
        zos.close();
        return baos.toByteArray();
        }
}










