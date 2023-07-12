package com.knits.enterprise.mapper.company;

import com.knits.enterprise.mapper.common.EntityMapper;
import com.knits.enterprise.mapper.security.UserMapper;
import com.knits.enterprise.model.company.JobTitle;
import com.knits.enterprise.dto.company.JobTitleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class})
public interface JobTitleMapper extends AbstractOrganizationStructureMapper<JobTitle, JobTitleDto> {

}
