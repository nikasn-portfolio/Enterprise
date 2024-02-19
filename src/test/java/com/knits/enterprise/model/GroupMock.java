package com.knits.enterprise.model;

import com.knits.enterprise.model.company.Group;

import java.util.HashSet;
import java.util.Set;

public class GroupMock {
    public static Group shallowGroupMock(long id){
        return Group.builder()
                .id(id)
                .name("Group Mock")
                .employees(new HashSet<>())
                .createdBy(null)
                .build();

    }
}
