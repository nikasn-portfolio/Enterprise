package com.knits.enterprise.mapper.company;

import com.knits.enterprise.dto.company.JobTitleDto;
import com.knits.enterprise.model.company.JobTitle;
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
public class JobTitleMapperImpl implements JobTitleMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 = DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" );

    @Override
    public List<JobTitle> toEntities(List<JobTitleDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<JobTitle>();
        }

        List<JobTitle> list = new ArrayList<JobTitle>( entityList.size() );
        for ( JobTitleDto jobTitleDto : entityList ) {
            list.add( toEntity( jobTitleDto ) );
        }

        return list;
    }

    @Override
    public List<JobTitleDto> toDtos(List<JobTitle> entityList) {
        if ( entityList == null ) {
            return new ArrayList<JobTitleDto>();
        }

        List<JobTitleDto> list = new ArrayList<JobTitleDto>( entityList.size() );
        for ( JobTitle jobTitle : entityList ) {
            list.add( toDto( jobTitle ) );
        }

        return list;
    }

    @Override
    public JobTitleDto toDto(JobTitle entity) {
        if ( entity == null ) {
            return null;
        }

        JobTitleDto.JobTitleDtoBuilder<?, ?> jobTitleDto = JobTitleDto.builder();

        if ( entity.getStartDate() != null ) {
            jobTitleDto.startDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getStartDate() ) );
        }
        if ( entity.getEndDate() != null ) {
            jobTitleDto.endDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getEndDate() ) );
        }
        jobTitleDto.active( entity.isActive() );
        jobTitleDto.id( entity.getId() );
        jobTitleDto.name( entity.getName() );
        jobTitleDto.description( entity.getDescription() );

        return jobTitleDto.build();
    }

    @Override
    public JobTitle toEntity(JobTitleDto dto) {
        if ( dto == null ) {
            return null;
        }

        JobTitle.JobTitleBuilder<?, ?> jobTitle = JobTitle.builder();

        if ( dto.getStartDate() != null ) {
            jobTitle.startDate( LocalDateTime.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        if ( dto.getEndDate() != null ) {
            jobTitle.endDate( LocalDateTime.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        jobTitle.id( dto.getId() );
        jobTitle.name( dto.getName() );
        jobTitle.description( dto.getDescription() );
        jobTitle.active( dto.isActive() );

        return jobTitle.build();
    }

    @Override
    public void partialUpdate(JobTitle entity, JobTitleDto dto) {
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
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
        entity.setActive( dto.isActive() );
    }

    @Override
    public void update(JobTitle entity, JobTitleDto dto) {
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
        entity.setName( dto.getName() );
        entity.setDescription( dto.getDescription() );
        entity.setActive( dto.isActive() );
    }
}
