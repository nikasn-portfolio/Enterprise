package com.knits.enterprise.service.common;

import com.knits.enterprise.dto.common.EmploymentContractDto;
import com.knits.enterprise.exceptions.UserException;
import com.knits.enterprise.mapper.common.EmploymentContractMapper;
import com.knits.enterprise.model.common.EmploymentContract;
import com.knits.enterprise.repository.common.EmploymentContractRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class EmploymentContractService {
    private EmploymentContractRepository employmentContractRepository;
    private EmploymentContractMapper employmentContractMapper;

    @Transactional
    public void saveNewEmploymentContract(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        EmploymentContract employmentContract = new EmploymentContract(fileName, file.getContentType(), file.getBytes());
        EmploymentContract savedEmploymentContract = employmentContractRepository.save(employmentContract);
        employmentContractMapper.toDto(savedEmploymentContract);

    }

    @Transactional
    public EmploymentContractDto findEmploymentContractById(Long id) {
        EmploymentContract employmentContract = employmentContractRepository.findById(id).orElseThrow(() ->
                new UserException("Employment contract id: " + id + " not found"));
        return employmentContractMapper.toDto(employmentContract);
    }

}