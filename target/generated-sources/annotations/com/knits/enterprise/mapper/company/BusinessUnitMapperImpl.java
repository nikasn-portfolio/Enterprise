package com.knits.enterprise.mapper.company;

import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.model.company.BusinessUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class BusinessUnitMapperImpl implements BusinessUnitMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 = DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" );

    @Override
    public List<BusinessUnit> toEntities(List<BusinessUnitDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<BusinessUnit>();
        }

        List<BusinessUnit> list = new ArrayList<BusinessUnit>( entityList.size() );
        for ( BusinessUnitDto businessUnitDto : entityList ) {
            list.add( toEntity( businessUnitDto ) );
        }

        return list;
    }

    @Override
    public List<BusinessUnitDto> toDtos(List<BusinessUnit> entityList) {
        if ( entityList == null ) {
            return new ArrayList<BusinessUnitDto>();
        }

        List<BusinessUnitDto> list = new ArrayList<BusinessUnitDto>( entityList.size() );
        for ( BusinessUnit businessUnit : entityList ) {
            list.add( toDto( businessUnit ) );
        }

        return list;
    }

    @Override
    public BusinessUnitDto toDto(BusinessUnit entity) {
        if ( entity == null ) {
            return null;
        }

        BusinessUnitDto.BusinessUnitDtoBuilder<?, ?> businessUnitDto = BusinessUnitDto.builder();

        if ( entity.getStartDate() != null ) {
            businessUnitDto.startDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getStartDate() ) );
        }
        if ( entity.getEndDate() != null ) {
            businessUnitDto.endDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getEndDate() ) );
        }
        businessUnitDto.active( entity.isActive() );
        businessUnitDto.id( entity.getId() );
        businessUnitDto.name( entity.getName() );
        businessUnitDto.description( entity.getDescription() );

        return businessUnitDto.build();
    }

    @Override
    public BusinessUnit toEntity(BusinessUnitDto dto) {
        if ( dto == null ) {
            return null;
        }

        BusinessUnit.BusinessUnitBuilder<?, ?> businessUnit = BusinessUnit.builder();

        if ( dto.getStartDate() != null ) {
            businessUnit.startDate( LocalDateTime.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        if ( dto.getEndDate() != null ) {
            businessUnit.endDate( LocalDateTime.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        businessUnit.id( dto.getId() );
        businessUnit.active( dto.isActive() );
        businessUnit.name( dto.getName() );
        businessUnit.description( dto.getDescription() );

        return businessUnit.build();
    }

    @Override
    public void partialUpdate(BusinessUnit entity, BusinessUnitDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getStartDate() != null ) {
            entity.setStartDate( LocalDateTime.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        if ( dto.getEndDate() != null ) {
            entity.setEndDate( LocalDateTime.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        else {
            entity.setEndDate( null );
        }
        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        entity.setActive( dto.isActive() );
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
    }

    @Override
    public void update(BusinessUnit entity, BusinessUnitDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getStartDate() != null ) {
            entity.setStartDate( LocalDateTime.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        else {
            entity.setStartDate( null );
        }
        if ( dto.getEndDate() != null ) {
            entity.setEndDate( LocalDateTime.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        else {
            entity.setEndDate( null );
        }
        entity.setId( dto.getId() );
        entity.setActive( dto.isActive() );
        entity.setName( dto.getName() );
        entity.setDescription( dto.getDescription() );
    }
}
