package com.knits.enterprise.service.company;

import com.knits.enterprise.dto.company.DivisionDto;
import com.knits.enterprise.exceptions.CompanyException;
import com.knits.enterprise.exceptions.UserException;
import com.knits.enterprise.mapper.company.DivisionMapper;
import com.knits.enterprise.mapper.security.UserMapper;
import com.knits.enterprise.model.company.Division;
import com.knits.enterprise.model.security.User;
import com.knits.enterprise.repository.company.DivisionRepository;
import com.knits.enterprise.repository.security.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class DivisionService {

    private final DivisionMapper divisionMapper;
    private final DivisionRepository divisionRepository;
    private final UserRepository userRepository;
    

    @Transactional
    public DivisionDto findDivisionById(Long id) {
        Division division = divisionRepository.findById(id).orElseThrow(() -> new CompanyException("Division #" + id + "not found"));
        return divisionMapper.toDto(division);
    }

    @Transactional
    public DivisionDto updateDivision(DivisionDto divisionDto) {
        Division divisionEntity = divisionRepository.findById(divisionDto.getId()).orElseThrow(() -> new CompanyException("Division not found"));
        divisionMapper.update(divisionEntity, divisionDto);
        return divisionMapper.toDto(divisionRepository.save(divisionEntity));
    }

    public void disableDivision(Long divisionId) throws Exception {
        Division divisionEntity = divisionRepository.findById(divisionId).orElseThrow(() -> new CompanyException("Division not found"));

        if (divisionEntity.isActive()) {
            divisionEntity.setActive(false);
            divisionRepository.save(divisionEntity);
        } else {
            throw new Exception("Division already disabled");
        }

    }

    public DivisionDto createNewDivision(Long userId, String name, String description) {
        Division newDivision = new Division();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("User #" + userId + " not found."));

        setNewDivisionData(name, description, newDivision, user);

        return divisionMapper.toDto(divisionRepository.save(newDivision));
    }

    private static void setNewDivisionData(String name, String description, Division newDivision, User user) {
        newDivision.setName(name);
        newDivision.setDescription(description);
        newDivision.setCreatedBy(user);
    }
}
