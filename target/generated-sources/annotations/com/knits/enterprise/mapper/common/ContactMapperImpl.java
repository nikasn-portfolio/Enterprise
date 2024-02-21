package com.knits.enterprise.mapper.common;

import com.knits.enterprise.dto.common.ContactDto;
import com.knits.enterprise.model.common.Contact;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T23:37:40+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public Contact toEntity(ContactDto dto) {
        if ( dto == null ) {
            return null;
        }

        Contact.ContactBuilder<?, ?> contact = Contact.builder();

        contact.id( dto.getId() );
        contact.firstName( dto.getFirstName() );
        contact.lastName( dto.getLastName() );
        contact.email( dto.getEmail() );
        contact.phoneNumber( dto.getPhoneNumber() );
        contact.jobTitle( dto.getJobTitle() );
        contact.note( dto.getNote() );

        return contact.build();
    }

    @Override
    public List<Contact> toEntities(List<ContactDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Contact>();
        }

        List<Contact> list = new ArrayList<Contact>( entityList.size() );
        for ( ContactDto contactDto : entityList ) {
            list.add( toEntity( contactDto ) );
        }

        return list;
    }

    @Override
    public ContactDto toDto(Contact entity) {
        if ( entity == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        contactDto.setId( entity.getId() );
        contactDto.setFirstName( entity.getFirstName() );
        contactDto.setLastName( entity.getLastName() );
        contactDto.setEmail( entity.getEmail() );
        contactDto.setPhoneNumber( entity.getPhoneNumber() );
        contactDto.setJobTitle( entity.getJobTitle() );
        contactDto.setNote( entity.getNote() );

        return contactDto;
    }

    @Override
    public List<ContactDto> toDtos(List<Contact> entityList) {
        if ( entityList == null ) {
            return new ArrayList<ContactDto>();
        }

        List<ContactDto> list = new ArrayList<ContactDto>( entityList.size() );
        for ( Contact contact : entityList ) {
            list.add( toDto( contact ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Contact entity, ContactDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getFirstName() != null ) {
            entity.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            entity.setLastName( dto.getLastName() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getPhoneNumber() != null ) {
            entity.setPhoneNumber( dto.getPhoneNumber() );
        }
        if ( dto.getJobTitle() != null ) {
            entity.setJobTitle( dto.getJobTitle() );
        }
        if ( dto.getNote() != null ) {
            entity.setNote( dto.getNote() );
        }
    }

    @Override
    public void update(Contact entity, ContactDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setFirstName( dto.getFirstName() );
        entity.setLastName( dto.getLastName() );
        entity.setEmail( dto.getEmail() );
        entity.setPhoneNumber( dto.getPhoneNumber() );
        entity.setJobTitle( dto.getJobTitle() );
        entity.setNote( dto.getNote() );
    }
}
