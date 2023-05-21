package com.knits.enterprise.mocks.dto;

import com.knits.enterprise.dto.company.DivisionDto;

public class DivisionDtoMocks {

    public static DivisionDto shallowDivisionDto(Long id) {

        return DivisionDto.builder()
                .id(1L)
                .name("A Mock name")
                .description("A Mock description")
                .build();
    }
}
