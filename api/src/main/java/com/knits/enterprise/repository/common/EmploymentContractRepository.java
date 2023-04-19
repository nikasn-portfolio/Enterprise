package com.knits.enterprise.repository.common;

import com.knits.enterprise.model.common.EmploymentContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentContractRepository extends JpaRepository<EmploymentContract, Long> {

}
