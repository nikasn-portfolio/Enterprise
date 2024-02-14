package com.knits.enterprise.repository.company;

import com.knits.enterprise.model.common.Organization;
import com.knits.enterprise.repository.common.ActiveEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends ActiveEntityRepository<Organization> {
}
