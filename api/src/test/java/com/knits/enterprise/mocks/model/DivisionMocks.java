package com.knits.enterprise.mocks.model;

import com.knits.enterprise.model.company.Division;
import java.util.ArrayList;
import java.util.List;

public class DivisionMocks {

    public static Division shallowDivision(Long id) {

        return Division.builder()
                .id(id)
                .name("A mock name")
                .description("A mock description")
                .build();
    }

    public static List<Division> shallowListOfDivisions(int howMany) {
        List<Division> mockDivisions = new ArrayList<>();

        for(int i = 0; i < howMany; i++) {
            mockDivisions.add(shallowDivision((long) i));
        }
        return mockDivisions;
    }
}
