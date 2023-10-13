package com.knits.enterprise.model;

import com.knits.enterprise.dto.search.GenericSearchDto;
import com.knits.enterprise.model.company.BusinessUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BusinessUnitMock {
    public static BusinessUnit shallowBusinessUnit(Long id){

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

    public static GenericSearchDto<BusinessUnit> createBusinessUnitSearchDto(int limit) {
        return new GenericSearchDto<BusinessUnit>(limit,0,"id",Sort.Direction.DESC);
    }

    public static Page<BusinessUnit> shallowPageOfBusinessUnits(int size) {
        List<BusinessUnit> businessUnitList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            businessUnitList.add(shallowBusinessUnit(Long.valueOf(i)));
        }

        // Create a PageRequest to specify the page number and size
        PageRequest pageRequest = PageRequest.of(0, size, Sort.by("id").descending());

        // Create a Page<BusinessUnit> using PageImpl
        return new PageImpl<>(businessUnitList, pageRequest, businessUnitList.size());
    }
}
