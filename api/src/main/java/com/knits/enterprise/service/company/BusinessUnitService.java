package com.knits.enterprise.service.company;

import com.knits.enterprise.config.Constants;
import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.search.GenericSearchDto;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class BusinessUnitService {
    private final BusinessUnitRepository businessUnitRepository;

    private final BusinessUnitMapper businessUnitMapper;
    private final UserMapper userMapper;

    @Transactional
    public BusinessUnitDto saveNewBusinessUnit(BusinessUnitDto businessUnitDto) {
        BusinessUnit businessUnit = businessUnitMapper.toEntity(businessUnitDto);
        businessUnit.setActive(true);
        businessUnit.setStartDate(LocalDateTime.now());
        businessUnit.setCreatedBy(userMapper.toEntity(Constants.hardcodedUserDto));
        BusinessUnit savedBusinessUnit = businessUnitRepository.save(businessUnit);
        return businessUnitMapper.toDto(savedBusinessUnit);
    }

    @Transactional
    public BusinessUnitDto partialUpdate(Long id,BusinessUnitDto businessUnitDto) {
        BusinessUnit businessUnit = businessUnitRepository.findById(id).orElseThrow(() -> new UserException("BusinessUnit#" + id + " not found"));
        businessUnitMapper.partialUpdate(businessUnit, businessUnitDto);
        businessUnitRepository.save(businessUnit);
        return businessUnitMapper.toDto(businessUnit);
    }

    @Transactional
    public BusinessUnitDto deactivateBusinessUnit(Long id) {
        BusinessUnit businessUnit = businessUnitRepository.findById(id).get();
        businessUnitRepository.delete(businessUnit);
        return businessUnitMapper.toDto(businessUnit);
    }

    @Transactional
    public PaginatedResponseDto<BusinessUnitDto> listAll(GenericSearchDto<BusinessUnit> searchDto) {

        Page<BusinessUnit> businessUnitPages = businessUnitRepository.findAll(searchDto.getSpecification(),searchDto.getPageable());
        List<BusinessUnitDto> businessUnitDtos = businessUnitMapper.toDtos(businessUnitPages.getContent());

        return PaginatedResponseDto.<BusinessUnitDto>builder()
                .page(searchDto.getPage())
                .size(businessUnitDtos.size())
                .sortingFields(searchDto.getSort())
                .sortDirection(searchDto.getDir().name())
                .data(businessUnitDtos)
                .build();
    }

}
