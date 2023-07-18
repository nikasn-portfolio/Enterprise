package com.knits.enterprise.service.company;

import com.knits.enterprise.dto.company.JobTitleDto;
import com.knits.enterprise.mapper.company.JobTitleMapper;
import com.knits.enterprise.model.company.JobTitle;
import com.knits.enterprise.model.security.User;
import com.knits.enterprise.repository.company.JobTitleRepository;
import com.knits.enterprise.service.security.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class JobTitleService {

    private final JobTitleMapper jobTitleMapper;
    private final JobTitleRepository jobTitleRepository;

    @Transactional
    public JobTitleDto saveNewJobTitle(JobTitleDto jobTitleDto) {
        JobTitle jobTitle = jobTitleMapper.toEntity(jobTitleDto);
        jobTitle.setStartDate(LocalDateTime.now());
        JobTitle savedJobTitle = jobTitleRepository.save(jobTitle);
        return jobTitleMapper.toDto(savedJobTitle);
    }

    public JobTitleDto deactivateJobTitle(Long jobTitleId) {

        JobTitle deactivateJobTitle = jobTitleRepository.findById(jobTitleId).orElse(null);
        if (deactivateJobTitle != null) {
            deactivateJobTitle.setEndDate(LocalDateTime.now());
            deactivateJobTitle.setActive(false);
            JobTitle savedJobTitle = jobTitleRepository.save(deactivateJobTitle);
            return jobTitleMapper.toDto(savedJobTitle);
        }
        return null;

    }
}
