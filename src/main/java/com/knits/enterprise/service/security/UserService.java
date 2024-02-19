package com.knits.enterprise.service.security;

import com.knits.enterprise.dto.security.UserDto;
import com.knits.enterprise.model.security.User;
import org.springframework.stereotype.Service;

/**
 * Service for managing {@link User}.
 */
@Service
public class UserService {
    public UserDto getCurrentUser() {
        return UserDto.builder()
                .id(1L)
                .firstName("mock-admin")
                .lastName("mock-admin")
                .login("mock-admin")
                .email("admin@enterprise.org")
                .password("not-hashed-pw-cant-use-for-login")
                .active(true)
                .build();
    }
}
