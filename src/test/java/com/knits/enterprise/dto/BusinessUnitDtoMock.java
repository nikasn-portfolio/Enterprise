package com.knits.enterprise.dto;

import com.knits.enterprise.config.Constants;
import com.knits.enterprise.dto.company.BusinessUnitDto;

import java.time.LocalDateTime;

public class BusinessUnitDtoMock {
    public static BusinessUnitDto createTestBusinessUnitDtoMock(Long id){
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
