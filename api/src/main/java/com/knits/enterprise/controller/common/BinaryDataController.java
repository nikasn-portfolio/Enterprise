package com.knits.enterprise.controller.common;

import com.knits.enterprise.service.common.BinaryDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/common/binaryData")
public class BinaryDataController {
    private final BinaryDataService binaryDataRepository;

    @PostMapping(value = "/saveTestPdfFile", produces = {"application/json"})
    public ResponseEntity saveTestPdfFile() {
        binaryDataRepository.saveTestPdfFile();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
