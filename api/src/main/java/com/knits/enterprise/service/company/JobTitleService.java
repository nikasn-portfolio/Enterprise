package com.knits.enterprise.service.company;

import com.knits.enterprise.dto.company.JobTitleDto;
import com.knits.enterprise.mapper.company.JobTitleMapper;
import com.knits.enterprise.repository.company.JobTitleRepository;
import org.springframework.stereotype.Service;

@Service
public class JobTitleService {

    private final JobTitleMapper jobTitleMapper;
    private final JobTitleRepository jobTitleRepository;

    public JobTitleDto saveNewJobTitle(JobTitleDto jobTitleDto) {
        JobTitle jobTitle = jobTitleMapper.toEntity(jobTitleDto);


        return null;
    }
}
