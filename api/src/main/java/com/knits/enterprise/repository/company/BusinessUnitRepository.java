package com.knits.enterprise.repository.company;

import com.knits.enterprise.model.company.BusinessUnit;
import com.knits.enterprise.repository.common.ActiveEntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessUnitRepository extends ActiveEntityRepository<BusinessUnit> {

    @Query("SELECT e from BusinessUnit e")
    List<BusinessUnit> findAllBusinessUnits();
}
