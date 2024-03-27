package com.knits.enterprise.model;

import com.knits.enterprise.model.company.Group;

import java.util.HashSet;

public class GroupMock {
    public static Group createTestGroupMock(long id){
        return Group.builder()
                .id(id)
                .name("Group Mock")
                .employees(new HashSet<>())
                .createdBy(null)
                .build();

    }
}
