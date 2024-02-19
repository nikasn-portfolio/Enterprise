package com.knits.enterprise.service.company;

import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.search.BusinessUnitSearchDto;
import com.knits.enterprise.exception.UserException;
import com.knits.enterprise.mapper.company.BusinessUnitMapper;
import com.knits.enterprise.mapper.security.UserMapper;
import com.knits.enterprise.model.company.BusinessUnit;
import com.knits.enterprise.repository.company.BusinessUnitRepository;
import com.knits.enterprise.service.security.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
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
        businessUnit.setCreatedBy(userMapper.toEntity(userService.getCurrentUser()));
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
    public BusinessUnitDto deactivateBusinessUnit(Long id) {
        BusinessUnit businessUnit = businessUnitRepository.findById(id).get();
        businessUnitRepository.delete(businessUnit);
        return businessUnitMapper.toDto(businessUnit);
    }


    public BusinessUnitDto findBusinessUnitById(Long id) {
        BusinessUnit businessUnit = businessUnitRepository.findById(id).orElseThrow(() -> new UserException("BusinessUnit#" + id + " not found"));
        return businessUnitMapper.toDto(businessUnit);
    }

    @Transactional
    public void deleteBusinessUnit(Long id) {
        List<BusinessUnit> allBusinessUnits = businessUnitRepository.findAllBusinessUnits();
        BusinessUnit businessUnit = allBusinessUnits.stream()
                .filter(unit -> unit.getId().equals(id))
                .findFirst()
                .orElseThrow((() -> new UserException("BusinessUnit#" + id + " not found")));
        businessUnitRepository.deleteBusinessUnitById(businessUnit.getId());
    }
    public PaginatedResponseDto<BusinessUnitDto> search(BusinessUnitSearchDto searchDto) {

        Page<BusinessUnit> businessUnitPages = businessUnitRepository.findAll(searchDto.getSpecification(),searchDto.getPageable());
        List<BusinessUnitDto> businessUnitDtos = businessUnitMapper.toDtos(businessUnitPages.getContent());

        return PaginatedResponseDto.<BusinessUnitDto>builder()
                .page(searchDto.getPage() != null ? searchDto.getPage() : 0)
                .size(businessUnitDtos.size())
                .sortingFields(searchDto.getSort())
                .sortDirection(searchDto.getDir().name())
                .data(businessUnitDtos)
                .build();
    }



}
