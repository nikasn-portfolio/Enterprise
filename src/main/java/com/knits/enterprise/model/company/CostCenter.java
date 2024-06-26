package com.knits.enterprise.model.company;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder=true)
@Table(name = "cost_center")
public class CostCenter extends OrganizationalEntity {
    @OneToMany(mappedBy = "costCenter")
    private List<Employee> employees;
}
