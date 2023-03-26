package com.knits.enterprise.service.company;


import com.knits.enterprise.dto.company.DivisionDto;
import com.knits.enterprise.exceptions.DivisionException;
import com.knits.enterprise.exceptions.UserException;
import com.knits.enterprise.mapper.company.DivisionMapper;
import com.knits.enterprise.model.company.Division;
import com.knits.enterprise.repository.company.DivisionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
               new UserException("Division#" + divisionDto.getId() + " not found, enter valid id"));
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
       Division division = divisionRepository.findById(id).orElseThrow(() -> new DivisionException("Division#" + id + " not found"));
       return divisionMapper.toDto(division);
   }
}
