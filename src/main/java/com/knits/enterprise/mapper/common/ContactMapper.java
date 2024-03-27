package com.knits.enterprise.mapper.common;

import com.knits.enterprise.dto.common.ContactDto;
import com.knits.enterprise.model.common.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper extends EntityMapper<Contact, ContactDto> {


}
