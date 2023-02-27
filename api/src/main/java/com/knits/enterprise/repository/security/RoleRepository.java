package com.knits.enterprise.repository.security;

import com.knits.enterprise.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

