package com.knits.enterprise.controller.company;

import com.knits.enterprise.dto.company.JobTitleDto;
import com.knits.enterprise.service.company.JobTitleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("JobTitleController")
class JobTitleControllerTest {

    @Mock
    private JobTitleService jobTitleService;

    @InjectMocks
    private JobTitleController jobTitleController;


    @Test
    @DisplayName("Should create a new job title and return a response entity with status OK")
    void createNewJobTitleReturnsResponseEntityWithStatusOk() {
        JobTitleDto jobTitleDto = new JobTitleDto();
        jobTitleDto.setName("Software Engineer");
        jobTitleDto.setDescription("Responsible for developing software applications");

        when(jobTitleService.saveNewJobTitle(jobTitleDto)).thenReturn(jobTitleDto);

        ResponseEntity<JobTitleDto> response = jobTitleController.createNewJobTitle(jobTitleDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobTitleDto, response.getBody());

        verify(jobTitleService, times(1)).saveNewJobTitle(jobTitleDto);
    }
}
