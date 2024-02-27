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
@Table(name = "business_unit")
public class BusinessUnit extends OrganizationalEntity {
    @OneToMany(mappedBy = "businessUnit")
    private List<Employee> employees;
}
