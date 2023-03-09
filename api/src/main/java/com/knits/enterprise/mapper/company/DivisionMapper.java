package com.knits.enterprise.mapper.company;


import com.knits.enterprise.mapper.security.UserMapper;
import com.knits.enterprise.model.company.Division;
import com.knits.enterprise.dto.company.DivisionDto;
import org.mapstruct.*;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class})
public interface DivisionMapper extends AbstractOrganizationStructureMapper<Division, DivisionDto> {


    @Mapping(target = "createdBy", ignore = true)
    DivisionDto toDto(Division division);


    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Division entity, DivisionDto dto);
}
