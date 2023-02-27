package com.knits.enterprise.repository.company;

import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.repository.common.ActiveEntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface EmployeeRepository extends ActiveEntityRepository<Employee> {


    @Query("select e from Employee e where e.id IN (:ids) AND e.active = true")
    Set<Employee> findAllById(@Param("ids") Set<Long> ids);


}
