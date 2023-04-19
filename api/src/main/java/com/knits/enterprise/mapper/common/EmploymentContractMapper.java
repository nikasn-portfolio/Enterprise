package com.knits.enterprise.mapper.common;

import com.knits.enterprise.dto.common.EmploymentContractDto;
import com.knits.enterprise.model.common.EmploymentContract;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmploymentContractMapper extends EntityMapper<EmploymentContract, EmploymentContractDto> {

}
