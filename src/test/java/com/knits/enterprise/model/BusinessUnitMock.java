package com.knits.enterprise.model;

import com.knits.enterprise.dto.search.BusinessUnitSearchDto;
import com.knits.enterprise.model.company.BusinessUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BusinessUnitMock {
    public static BusinessUnit createTestBusinessUnitMock(Long id){

        return BusinessUnit.builder()
                .id(id)
                .name("Mock name")
                .createdBy(null)
                .description("Mock desc")
                .active(true)
                .startDate(LocalDateTime.now())
                .active(true)
                .build();
    }

    public static BusinessUnitSearchDto createBusinessUnitSearchDto(int limit) {
        return new BusinessUnitSearchDto().toBuilder().limit(limit).build();
    }

    public static Page<BusinessUnit> createTestSetOfBusinessUnitMock(int size) {
        List<BusinessUnit> businessUnitList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            businessUnitList.add(createTestBusinessUnitMock(Long.valueOf(i)));
        }

        // Create a PageRequest to specify the page number and size
        PageRequest pageRequest = PageRequest.of(0, size, Sort.by("id").descending());

        // Create a Page<BusinessUnit> using PageImpl
        return new PageImpl<>(businessUnitList, pageRequest, businessUnitList.size());
    }
}
