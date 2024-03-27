package com.knits.enterprise.repository.common;

import com.knits.enterprise.model.company.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>, JpaSpecificationExecutor<Contract> {
    List<Contract> findByEmployee_Id(Long employeeId);

    @Query("select c.id from Contract c")
    List<Long> findAllIds();

    @Transactional
    @Query("select new com.knits.enterprise.model.company.Contract(c.id, c.mediaFile, c.employee, c.active, c.createdAt) from Contract c where c.id = :id")
    Optional<Contract> findById(Long id);
}
