package com.knits.enterprise.repository.company;

import com.knits.enterprise.model.company.BusinessUnit;
import com.knits.enterprise.repository.common.ActiveEntityRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BusinessUnitRepository extends ActiveEntityRepository<BusinessUnit> {

    @Query("SELECT e from BusinessUnit e")
    List<BusinessUnit> findAllBusinessUnits();

    @Modifying
    @Transactional
    @Query("DELETE FROM BusinessUnit e WHERE e.id = :id")
    void deleteBusinessUnitById(@Param("id") Long id);
}
