package com.knits.enterprise.mapper.common;

import com.knits.enterprise.dto.common.AddressDto;
import com.knits.enterprise.dto.common.CountryDto;
import com.knits.enterprise.model.common.Address;
import com.knits.enterprise.model.common.Country;
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
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toEntity(AddressDto dto) {
        if ( dto == null ) {
            return null;
        }

        Address.AddressBuilder<?, ?> address = Address.builder();

        address.country( countryDtoToCountry( dto.getCountry() ) );
        address.city( dto.getCity() );
        address.street( dto.getStreet() );

        return address.build();
    }

    @Override
    public List<Address> toEntities(List<AddressDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Address>();
        }

        List<Address> list = new ArrayList<Address>( entityList.size() );
        for ( AddressDto addressDto : entityList ) {
            list.add( toEntity( addressDto ) );
        }

        return list;
    }

    @Override
    public AddressDto toDto(Address entity) {
        if ( entity == null ) {
            return null;
        }

        AddressDto.AddressDtoBuilder addressDto = AddressDto.builder();

        addressDto.country( countryToCountryDto( entity.getCountry() ) );
        addressDto.city( entity.getCity() );
        addressDto.street( entity.getStreet() );

        return addressDto.build();
    }

    @Override
    public List<AddressDto> toDtos(List<Address> entityList) {
        if ( entityList == null ) {
            return new ArrayList<AddressDto>();
        }

        List<AddressDto> list = new ArrayList<AddressDto>( entityList.size() );
        for ( Address address : entityList ) {
            list.add( toDto( address ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Address entity, AddressDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getCountry() != null ) {
            if ( entity.getCountry() == null ) {
                entity.setCountry( Country.builder().build() );
            }
            countryDtoToCountry1( dto.getCountry(), entity.getCountry() );
        }
        if ( dto.getCity() != null ) {
            entity.setCity( dto.getCity() );
        }
        if ( dto.getStreet() != null ) {
            entity.setStreet( dto.getStreet() );
        }
    }

    @Override
    public void update(Address entity, AddressDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getCountry() != null ) {
            if ( entity.getCountry() == null ) {
                entity.setCountry( Country.builder().build() );
            }
            countryDtoToCountry1( dto.getCountry(), entity.getCountry() );
        }
        else {
            entity.setCountry( null );
        }
        entity.setCity( dto.getCity() );
        entity.setStreet( dto.getStreet() );
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

    protected void countryDtoToCountry1(CountryDto countryDto, Country mappingTarget) {
        if ( countryDto == null ) {
            return;
        }

        mappingTarget.setId( countryDto.getId() );
        mappingTarget.setIso2( countryDto.getIso2() );
        mappingTarget.setIso3( countryDto.getIso3() );
        mappingTarget.setName( countryDto.getName() );
    }
}
