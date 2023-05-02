package com.knits.enterprise.service.common;

import com.knits.enterprise.dto.common.EmploymentContractDto;
import com.knits.enterprise.dto.search.EmploymentContractSearchDto;
import com.knits.enterprise.mapper.common.EmploymentContractMapper;
import com.knits.enterprise.model.common.EmploymentContract;
import com.knits.enterprise.repository.common.EmploymentContractRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        employmentContract.setActive(true);
        EmploymentContract savedEmploymentContract = employmentContractRepository.save(employmentContract);
        employmentContractMapper.toDto(savedEmploymentContract);
    }

    @Transactional
    public List<EmploymentContractDto> findAllEmploymentContracts(EmploymentContractSearchDto<EmploymentContract> searchDto) {
        List<EmploymentContract> employmentContractList = employmentContractRepository.findAll(searchDto.getSpecification());
        return employmentContractMapper.toDtos(employmentContractList);
    }

    @Transactional
    public List<Long> findAllEmploymentContractsIds() {
        return employmentContractRepository.getAllIds();
    }

    @Transactional
    public List<EmploymentContract> findAllEmploymentContractsByIds(List<Long> ids) {
        return employmentContractRepository.findAllById(ids);
    }

    @Transactional
    public Optional<EmploymentContract> findAllEmploymentContractsById(long id) {
        return employmentContractRepository.findById(id);
    }

    public byte [] generateZip(List<EmploymentContractDto> employmentContracts) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (EmploymentContractDto contracts : employmentContracts) {
            ZipEntry entry = new ZipEntry(contracts.getFileName());
            entry.setSize(employmentContracts.size());
            zos.putNextEntry(entry);
            zos.write(contracts.getData());
        }
        zos.closeEntry();
        zos.close();
        return baos.toByteArray();
    }

}