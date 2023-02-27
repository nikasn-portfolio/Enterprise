package com.knits.enterprise.mapper.company;

import com.knits.enterprise.dto.company.GroupDto;
import com.knits.enterprise.mapper.common.EntityMapper;
import com.knits.enterprise.mapper.security.UserMapper;
import com.knits.enterprise.model.company.Group;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class})
public interface GroupMapper extends AbstractOrganizationStructureMapper<Group, GroupDto> {


}
