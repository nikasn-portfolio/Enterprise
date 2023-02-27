package com.knits.enterprise.mapper.common;

import com.knits.enterprise.dto.common.AddressDto;
import com.knits.enterprise.model.common.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<Address, AddressDto> {
}
