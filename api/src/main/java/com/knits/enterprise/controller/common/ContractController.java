package com.knits.enterprise.controller.common;

import com.knits.enterprise.service.company.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/common/contracts")
public class ContractController {
    private final ContractService contractService;

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<List<Long>> findAllContracts() {
        return ResponseEntity.ok(contractService.findContractsIds());
    }
    @GetMapping("")
    public ResponseEntity getEmployeeContracts(@RequestParam List<Long> ids) {
        byte[] bytes = contractService.makeContractsZipFileByIds(ids);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "employees.zip");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return ResponseEntity.ok().headers(headers).body(bytes);
    }


}
