package com.knits.enterprise.mapper.common;

import com.knits.enterprise.dto.common.CountryDto;
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
public class CountryMapperImpl implements CountryMapper {

    @Override
    public Country toEntity(CountryDto dto) {
        if ( dto == null ) {
            return null;
        }

        Country.CountryBuilder country = Country.builder();

        country.id( dto.getId() );
        country.iso2( dto.getIso2() );
        country.iso3( dto.getIso3() );
        country.name( dto.getName() );

        return country.build();
    }

    @Override
    public List<Country> toEntities(List<CountryDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Country>();
        }

        List<Country> list = new ArrayList<Country>( entityList.size() );
        for ( CountryDto countryDto : entityList ) {
            list.add( toEntity( countryDto ) );
        }

        return list;
    }

    @Override
    public CountryDto toDto(Country entity) {
        if ( entity == null ) {
            return null;
        }

        CountryDto.CountryDtoBuilder countryDto = CountryDto.builder();

        countryDto.id( entity.getId() );
        countryDto.iso2( entity.getIso2() );
        countryDto.iso3( entity.getIso3() );
        countryDto.name( entity.getName() );

        return countryDto.build();
    }

    @Override
    public List<CountryDto> toDtos(List<Country> entityList) {
        if ( entityList == null ) {
            return new ArrayList<CountryDto>();
        }

        List<CountryDto> list = new ArrayList<CountryDto>( entityList.size() );
        for ( Country country : entityList ) {
            list.add( toDto( country ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Country entity, CountryDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getIso2() != null ) {
            entity.setIso2( dto.getIso2() );
        }
        if ( dto.getIso3() != null ) {
            entity.setIso3( dto.getIso3() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
    }

    @Override
    public void update(Country entity, CountryDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setIso2( dto.getIso2() );
        entity.setIso3( dto.getIso3() );
        entity.setName( dto.getName() );
    }
}
