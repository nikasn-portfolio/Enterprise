package com.knits.enterprise.mapper.security;

import com.knits.enterprise.model.security.User;
import com.knits.enterprise.dto.security.UserDto;
import com.knits.enterprise.mapper.common.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper extends EntityMapper<User, UserDto> {



}