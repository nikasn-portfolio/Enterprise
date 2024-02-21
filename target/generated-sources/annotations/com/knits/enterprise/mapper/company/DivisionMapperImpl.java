package com.knits.enterprise.mapper.company;

import com.knits.enterprise.dto.company.DivisionDto;
import com.knits.enterprise.model.company.Division;
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
public class DivisionMapperImpl implements DivisionMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 = DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" );

    @Override
    public List<Division> toEntities(List<DivisionDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Division>();
        }

        List<Division> list = new ArrayList<Division>( entityList.size() );
        for ( DivisionDto divisionDto : entityList ) {
            list.add( toEntity( divisionDto ) );
        }

        return list;
    }

    @Override
    public List<DivisionDto> toDtos(List<Division> entityList) {
        if ( entityList == null ) {
            return new ArrayList<DivisionDto>();
        }

        List<DivisionDto> list = new ArrayList<DivisionDto>( entityList.size() );
        for ( Division division : entityList ) {
            list.add( toDto( division ) );
        }

        return list;
    }

    @Override
    public Division toEntity(DivisionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Division.DivisionBuilder<?, ?> division = Division.builder();

        if ( dto.getStartDate() != null ) {
            division.startDate( LocalDateTime.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        if ( dto.getEndDate() != null ) {
            division.endDate( LocalDateTime.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        division.id( dto.getId() );
        division.active( dto.isActive() );
        division.name( dto.getName() );
        division.description( dto.getDescription() );

        return division.build();
    }

    @Override
    public void partialUpdate(Division entity, DivisionDto dto) {
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
    public void update(Division entity, DivisionDto dto) {
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

    @Override
    public DivisionDto toDto(Division division) {
        if ( division == null ) {
            return null;
        }

        DivisionDto.DivisionDtoBuilder<?, ?> divisionDto = DivisionDto.builder();

        divisionDto.active( division.isActive() );
        divisionDto.id( division.getId() );
        divisionDto.name( division.getName() );
        divisionDto.description( division.getDescription() );
        if ( division.getStartDate() != null ) {
            divisionDto.startDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( division.getStartDate() ) );
        }
        if ( division.getEndDate() != null ) {
            divisionDto.endDate( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( division.getEndDate() ) );
        }

        return divisionDto.build();
    }
}
