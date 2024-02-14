package com.knits.enterprise.controller.company;

import com.knits.enterprise.dto.company.JobTitleDto;
import com.knits.enterprise.service.company.JobTitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class JobTitleController {

    @Autowired
    private JobTitleService jobTitleService;


    @PostMapping(value = "/jobtitles", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<JobTitleDto> createNewJobTitle(@RequestBody JobTitleDto jobTitleDto) {
        log.debug("REST request to create JobTitle");
        return ResponseEntity
                .ok()
                .body(jobTitleService.saveNewJobTitle(jobTitleDto));
    }

    @PatchMapping(value = "/jobtitles/deactivate")
    public ResponseEntity<JobTitleDto> deactivateJobTitle(@RequestParam final Long id) {
        log.debug("REST request to deactivate JobTitle : {}", id);
        JobTitleDto deactivateJobTitle = jobTitleService.deactivateJobTitle(id);
        if (deactivateJobTitle != null) {
            return ResponseEntity
                    .ok()
                    .body(deactivateJobTitle);
        } else {
            return ResponseEntity
                    .notFound()
                    .build();

        }

    }
}
