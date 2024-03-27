package com.knits.enterprise.util.excel.company;

import com.knits.enterprise.dto.security.UserDto;

public class UserUtil {
    public static UserDto getFakeAuthenticatedUserDto(){
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
