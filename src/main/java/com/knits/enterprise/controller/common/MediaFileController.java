package com.knits.enterprise.controller.common;

import com.knits.enterprise.service.common.MediaFileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MediaFileController {
    private final MediaFileService mediaFileService;

    @PostMapping(value = "/media-file-service/media-files/test-file", produces = "application/json")
    public ResponseEntity saveTestPdfFile() {
        mediaFileService.saveTestPdfFile();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
