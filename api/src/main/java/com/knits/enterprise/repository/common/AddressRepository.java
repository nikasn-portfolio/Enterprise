package com.knits.enterprise.repository.common;

import com.knits.enterprise.model.common.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
