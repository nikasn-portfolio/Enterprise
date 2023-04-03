package com.knits.enterprise.service.company;


import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.DivisionDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.search.GenericSearchDto;
import com.knits.enterprise.exceptions.DivisionException;
import com.knits.enterprise.exceptions.UserException;
import com.knits.enterprise.mapper.company.DivisionMapper;
import com.knits.enterprise.model.company.Division;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.repository.company.DivisionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class DivisionService {


    private final DivisionRepository divisionRepository;
    private final DivisionMapper divisionMapper;


    @Transactional
    public DivisionDto saveNewDivision(DivisionDto divisionDto) {
        Division division = divisionMapper.toEntity(divisionDto);
        Division savedDivision = divisionRepository.save(division);
        return divisionMapper.toDto(savedDivision);
    }


    @Transactional
    public DivisionDto partialUpdate(DivisionDto divisionDto) {
        Division division = divisionRepository.findById(divisionDto.getId()).orElseThrow(() ->
                new UserException("Division id:" + divisionDto.getId() + " not found, enter valid id"));
        divisionRepository.save(division);
        divisionMapper.partialUpdate(division, divisionDto);
        return divisionMapper.toDto(division);
    }


    @Transactional
    public DivisionDto deleteDivision(Long id) {
        Division division = divisionRepository.getById(id);
        divisionRepository.delete(division);
        return divisionMapper.toDto(division);
    }


    @Transactional
    public DivisionDto findDivisionById(Long id) {
        Division division = divisionRepository.findById(id).orElseThrow(() ->
                new UserException("Division id: " + id + " not found"));
        return divisionMapper.toDto(division);
    }


    @Transactional
    public PaginatedResponseDto<DivisionDto> findAllDivision(GenericSearchDto<Division> searchDto) {

        Page<Division> divisionsPage = divisionRepository.findAll(searchDto.getSpecification(), searchDto.getPageable());
        List<DivisionDto> divisionDtos = divisionMapper.toDtos(divisionsPage.getContent());

        return PaginatedResponseDto.<DivisionDto>builder()
                .page(searchDto.getPage())
                .size(divisionDtos.size())
                .sortingFields(searchDto.getSort())
                .sortDirection(searchDto.getDir().name())
                .data(divisionDtos)
                .build();
    }
}
