package com.knits.enterprise.model;

import com.knits.enterprise.config.Constants;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class BusinessUnitDtoMock {
    public static BusinessUnitDto shallowBusinessUnitDto(Long id){
        return BusinessUnitDto.builder()
                .id(id)
                .name("Mock name")
                .createdBy(null)
                .description("Mock desc")
                .active(true)
                .startDate(LocalDateTime.now().format(Constants.TIME_FORMATTER))
                .active(true)
                .build();
    }
}
