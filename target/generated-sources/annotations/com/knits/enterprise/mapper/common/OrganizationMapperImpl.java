package com.knits.enterprise.mapper.common;

import com.knits.enterprise.dto.common.ContactDto;
import com.knits.enterprise.dto.common.CountryDto;
import com.knits.enterprise.dto.common.OrganizationDto;
import com.knits.enterprise.model.common.Contact;
import com.knits.enterprise.model.common.Country;
import com.knits.enterprise.model.common.Organization;
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
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public Organization toEntity(OrganizationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Organization.OrganizationBuilder<?, ?> organization = Organization.builder();

        organization.id( dto.getId() );
        organization.name( dto.getName() );
        organization.alias( dto.getAlias() );
        organization.vatNumber( dto.getVatNumber() );
        organization.registrationCode( dto.getRegistrationCode() );
        organization.taxRegistrationCountry( countryDtoToCountry( dto.getTaxRegistrationCountry() ) );
        organization.contactPerson( contactDtoToContact( dto.getContactPerson() ) );

        return organization.build();
    }

    @Override
    public List<Organization> toEntities(List<OrganizationDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Organization>();
        }

        List<Organization> list = new ArrayList<Organization>( entityList.size() );
        for ( OrganizationDto organizationDto : entityList ) {
            list.add( toEntity( organizationDto ) );
        }

        return list;
    }

    @Override
    public OrganizationDto toDto(Organization entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationDto.OrganizationDtoBuilder organizationDto = OrganizationDto.builder();

        organizationDto.id( entity.getId() );
        organizationDto.name( entity.getName() );
        organizationDto.alias( entity.getAlias() );
        organizationDto.vatNumber( entity.getVatNumber() );
        organizationDto.registrationCode( entity.getRegistrationCode() );
        organizationDto.taxRegistrationCountry( countryToCountryDto( entity.getTaxRegistrationCountry() ) );
        organizationDto.contactPerson( contactToContactDto( entity.getContactPerson() ) );

        return organizationDto.build();
    }

    @Override
    public List<OrganizationDto> toDtos(List<Organization> entityList) {
        if ( entityList == null ) {
            return new ArrayList<OrganizationDto>();
        }

        List<OrganizationDto> list = new ArrayList<OrganizationDto>( entityList.size() );
        for ( Organization organization : entityList ) {
            list.add( toDto( organization ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Organization entity, OrganizationDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getAlias() != null ) {
            entity.setAlias( dto.getAlias() );
        }
        if ( dto.getVatNumber() != null ) {
            entity.setVatNumber( dto.getVatNumber() );
        }
        if ( dto.getRegistrationCode() != null ) {
            entity.setRegistrationCode( dto.getRegistrationCode() );
        }
        if ( dto.getTaxRegistrationCountry() != null ) {
            if ( entity.getTaxRegistrationCountry() == null ) {
                entity.setTaxRegistrationCountry( Country.builder().build() );
            }
            countryDtoToCountry1( dto.getTaxRegistrationCountry(), entity.getTaxRegistrationCountry() );
        }
        if ( dto.getContactPerson() != null ) {
            if ( entity.getContactPerson() == null ) {
                entity.setContactPerson( Contact.builder().build() );
            }
            contactDtoToContact1( dto.getContactPerson(), entity.getContactPerson() );
        }
    }

    @Override
    public void update(Organization entity, OrganizationDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setName( dto.getName() );
        entity.setAlias( dto.getAlias() );
        entity.setVatNumber( dto.getVatNumber() );
        entity.setRegistrationCode( dto.getRegistrationCode() );
        if ( dto.getTaxRegistrationCountry() != null ) {
            if ( entity.getTaxRegistrationCountry() == null ) {
                entity.setTaxRegistrationCountry( Country.builder().build() );
            }
            countryDtoToCountry1( dto.getTaxRegistrationCountry(), entity.getTaxRegistrationCountry() );
        }
        else {
            entity.setTaxRegistrationCountry( null );
        }
        if ( dto.getContactPerson() != null ) {
            if ( entity.getContactPerson() == null ) {
                entity.setContactPerson( Contact.builder().build() );
            }
            contactDtoToContact1( dto.getContactPerson(), entity.getContactPerson() );
        }
        else {
            entity.setContactPerson( null );
        }
    }

    protected Country countryDtoToCountry(CountryDto countryDto) {
        if ( countryDto == null ) {
            return null;
        }

        Country.CountryBuilder country = Country.builder();

        country.id( countryDto.getId() );
        country.iso2( countryDto.getIso2() );
        country.iso3( countryDto.getIso3() );
        country.name( countryDto.getName() );

        return country.build();
    }

    protected Contact contactDtoToContact(ContactDto contactDto) {
        if ( contactDto == null ) {
            return null;
        }

        Contact.ContactBuilder<?, ?> contact = Contact.builder();

        contact.id( contactDto.getId() );
        contact.firstName( contactDto.getFirstName() );
        contact.lastName( contactDto.getLastName() );
        contact.email( contactDto.getEmail() );
        contact.phoneNumber( contactDto.getPhoneNumber() );
        contact.jobTitle( contactDto.getJobTitle() );
        contact.note( contactDto.getNote() );

        return contact.build();
    }

    protected CountryDto countryToCountryDto(Country country) {
        if ( country == null ) {
            return null;
        }

        CountryDto.CountryDtoBuilder countryDto = CountryDto.builder();

        countryDto.id( country.getId() );
        countryDto.iso2( country.getIso2() );
        countryDto.iso3( country.getIso3() );
        countryDto.name( country.getName() );

        return countryDto.build();
    }

    protected ContactDto contactToContactDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        contactDto.setId( contact.getId() );
        contactDto.setFirstName( contact.getFirstName() );
        contactDto.setLastName( contact.getLastName() );
        contactDto.setEmail( contact.getEmail() );
        contactDto.setPhoneNumber( contact.getPhoneNumber() );
        contactDto.setJobTitle( contact.getJobTitle() );
        contactDto.setNote( contact.getNote() );

        return contactDto;
    }

    protected void countryDtoToCountry1(CountryDto countryDto, Country mappingTarget) {
        if ( countryDto == null ) {
            return;
        }

        mappingTarget.setId( countryDto.getId() );
        mappingTarget.setIso2( countryDto.getIso2() );
        mappingTarget.setIso3( countryDto.getIso3() );
        mappingTarget.setName( countryDto.getName() );
    }

    protected void contactDtoToContact1(ContactDto contactDto, Contact mappingTarget) {
        if ( contactDto == null ) {
            return;
        }

        mappingTarget.setId( contactDto.getId() );
        mappingTarget.setFirstName( contactDto.getFirstName() );
        mappingTarget.setLastName( contactDto.getLastName() );
        mappingTarget.setEmail( contactDto.getEmail() );
        mappingTarget.setPhoneNumber( contactDto.getPhoneNumber() );
        mappingTarget.setJobTitle( contactDto.getJobTitle() );
        mappingTarget.setNote( contactDto.getNote() );
    }
}
