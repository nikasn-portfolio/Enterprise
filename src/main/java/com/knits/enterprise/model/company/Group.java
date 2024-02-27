package com.knits.enterprise.model.company;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Table(name = "[group]")
public class Group extends OrganizationalEntity {
    @ManyToMany
    @JoinTable(
            name = "employees_groups",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "employee_id") }
    )
    private Set<Employee> employees;



}
