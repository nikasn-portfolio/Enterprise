package com.knits.enterprise.mapper.company;

import com.knits.enterprise.dto.company.CostCenterDto;
import com.knits.enterprise.model.company.CostCenter;
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
public class CostCenterMapperImpl implements CostCenterMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 = DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" );

    @Override
    public List<CostCenter> toEntities(List<CostCenterDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<CostCenter>();
        }

        List<CostCenter> list = new ArrayList<CostCenter>( entityList.size() );
        for ( CostCenterDto costCenterDto : entityList ) {
            list.add( toEntity( costCenterDto ) );
        }

        return list;
    }

    @Override
    public List<CostCenterDto> toDtos(List<CostCenter> entityList) {
        if ( entityList == null ) {
            return new ArrayList<CostCenterDto>();
        }

        List<CostCenterDto> list = new ArrayList<CostCenterDto>( entityList.size() );
        for ( CostCenter costCenter : entityList ) {
            list.add( toDto( costCenter ) );
        }

        return list;
    }

    @Override
    public CostCenterDto toDto(CostCenter entity) {
        if ( entity == null ) {
            return null;
        }

        CostCenterDto.CostCenterDtoBuilder<?, ?> costCenterDto = CostCenterDto.builder();

        if ( entity.getStartDate() != null ) {
            costCenterDto.startDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getStartDate() ) );
        }
        if ( entity.getEndDate() != null ) {
            costCenterDto.endDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getEndDate() ) );
        }
        costCenterDto.active( entity.isActive() );
        costCenterDto.id( entity.getId() );
        costCenterDto.name( entity.getName() );
        costCenterDto.description( entity.getDescription() );

        return costCenterDto.build();
    }

    @Override
    public CostCenter toEntity(CostCenterDto dto) {
        if ( dto == null ) {
            return null;
        }

        CostCenter.CostCenterBuilder<?, ?> costCenter = CostCenter.builder();

        if ( dto.getStartDate() != null ) {
            costCenter.startDate( LocalDateTime.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        if ( dto.getEndDate() != null ) {
            costCenter.endDate( LocalDateTime.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        costCenter.id( dto.getId() );
        costCenter.active( dto.isActive() );
        costCenter.name( dto.getName() );
        costCenter.description( dto.getDescription() );

        return costCenter.build();
    }

    @Override
    public void partialUpdate(CostCenter entity, CostCenterDto dto) {
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
    public void update(CostCenter entity, CostCenterDto dto) {
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
