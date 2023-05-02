package com.knits.enterprise.controller.common;

import com.knits.enterprise.dto.common.EmploymentContractDto;
import com.knits.enterprise.dto.search.EmploymentContractSearchDto;
import com.knits.enterprise.http.ResponseMessage;
import com.knits.enterprise.model.common.EmploymentContract;
import com.knits.enterprise.service.common.EmploymentContractService;
import com.knits.enterprise.validation.FileValidation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.tags.form.InputTag;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
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
    @GetMapping(value = "employment-contracts-zip", produces = "application/zip")
    public byte[] zipDownload(EmploymentContractSearchDto<EmploymentContract> searchDto) throws IOException {

        List<EmploymentContractDto> employmentContracts = employmentContractService.findAllEmploymentContracts(searchDto);
        return employmentContractService.generateZip(employmentContracts);
    }

    // api/employees/contracts/all @Return a list of contract ids
    @GetMapping(value = "employees/contracts/all", produces = {"application/json"})
    public List<Long> getAllEmploymentContractIds() {
        return employmentContractService.findAllEmploymentContractsIds();
    }

    //api/employees/contracts/ids=1,2,3
    //Returns all documents fetched by ids in a .zip file.
    //For every id that is not found, a text file should be generated (and added to zip) with missing id as filename and a default text.
    @GetMapping(value = "employees/contracts/{id}", produces = "application/zip")
    public byte[] getByIdZip(@PathVariable(value = "id") final List<Long> ids) throws IOException {

        // to determine which Ids are not present
        Map<Long, Boolean> map = new HashMap<>();

        for (Long id : ids) {
            if (employmentContractService.findAllEmploymentContractsById(id).isEmpty()) {
                map.put(id, false);
            } else {
                map.put(id, true);
            }
        }

        List<Long> keyList = new ArrayList<>(map.keySet());

        List<EmploymentContract> employmentContracts = employmentContractService.findAllEmploymentContractsByIds(ids);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (int i = 0; i < map.size(); i++) {

            if(map.get(keyList.get(i)) && employmentContractService.findAllEmploymentContractsById(ids.get(i)).isPresent()) {

                ZipEntry entry = new ZipEntry(employmentContracts.get(i).getFileName());
                entry.setSize(employmentContracts.size());
                zos.putNextEntry(entry);
                zos.write(employmentContracts.get(i).getData());

            } else {
                File myTxtFile = new File(ids.get(i).toString());
                FileWriter myWriter = new FileWriter(ids.get(i).toString());
                myWriter.write("Employment contract not found!");
                myWriter.close();

                ZipEntry entry = new ZipEntry(ids.get(i).toString());
                entry.setSize(map.size());
                zos.putNextEntry(entry);
                zos.write(Files.readAllBytes(myTxtFile.toPath()));
            }
        }
        zos.closeEntry();
        zos.close();
        return baos.toByteArray();
    }
}
