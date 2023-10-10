package com.knits.enterprise.service.company;

import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.security.UserDto;
import com.knits.enterprise.exceptions.UserException;
import com.knits.enterprise.mapper.company.BusinessUnitMapper;
import com.knits.enterprise.mapper.security.UserMapper;
import com.knits.enterprise.model.company.BusinessUnit;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.repository.company.BusinessUnitRepository;
import com.knits.enterprise.repository.security.UserRepository;
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
public class BusinessUnitService {
    private final BusinessUnitRepository businessUnitRepository;

    private final BusinessUnitMapper businessUnitMapper;

    private final UserMapper userMapper;

    private final UserService userService;

    @Transactional
    public BusinessUnitDto saveNewBusinessUnit(BusinessUnitDto businessUnitDto) {
        BusinessUnit businessUnit = businessUnitMapper.toEntity(businessUnitDto);
        businessUnit.setActive(true);
        businessUnit.setStartDate(LocalDateTime.now());
        businessUnit.setCreatedBy(userMapper.toEntity(userService.getCurrentHardCodedUser()));
        BusinessUnit savedBusinessUnit = businessUnitRepository.save(businessUnit);
        return businessUnitMapper.toDto(savedBusinessUnit);
    }

    @Transactional
    public BusinessUnitDto partialUpdate(BusinessUnitDto businessUnitDto) {
        BusinessUnit businessUnit = businessUnitRepository.findById(businessUnitDto.getId()).orElseThrow(() -> new UserException("BusinessUnit#" + businessUnitDto.getId() + " not found"));
        businessUnitMapper.partialUpdate(businessUnit, businessUnitDto);
        businessUnitRepository.save(businessUnit);
        return businessUnitMapper.toDto(businessUnit);
    }

    @Transactional
    public BusinessUnitDto deactivateBusinessUnit(BusinessUnitDto businessUnitDto) {
        BusinessUnit businessUnit = businessUnitRepository.findById(businessUnitDto.getId()).orElseThrow(() -> new UserException("BusinessUnit#" + businessUnitDto.getId() + " not found"));
        businessUnit.setEndDate(LocalDateTime.now());
        businessUnit.setActive(false);
        businessUnitRepository.save(businessUnit);
        return businessUnitMapper.toDto(businessUnit);
    }

}
