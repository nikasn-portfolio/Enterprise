package com.knits.enterprise.mapper.company;

import com.knits.enterprise.dto.company.TeamDto;
import com.knits.enterprise.model.company.Team;
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
public class TeamMapperImpl implements TeamMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 = DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" );

    @Override
    public List<Team> toEntities(List<TeamDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Team>();
        }

        List<Team> list = new ArrayList<Team>( entityList.size() );
        for ( TeamDto teamDto : entityList ) {
            list.add( toEntity( teamDto ) );
        }

        return list;
    }

    @Override
    public List<TeamDto> toDtos(List<Team> entityList) {
        if ( entityList == null ) {
            return new ArrayList<TeamDto>();
        }

        List<TeamDto> list = new ArrayList<TeamDto>( entityList.size() );
        for ( Team team : entityList ) {
            list.add( toDto( team ) );
        }

        return list;
    }

    @Override
    public TeamDto toDto(Team entity) {
        if ( entity == null ) {
            return null;
        }

        TeamDto.TeamDtoBuilder<?, ?> teamDto = TeamDto.builder();

        if ( entity.getStartDate() != null ) {
            teamDto.startDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getStartDate() ) );
        }
        if ( entity.getEndDate() != null ) {
            teamDto.endDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getEndDate() ) );
        }
        teamDto.active( entity.isActive() );
        teamDto.id( entity.getId() );
        teamDto.name( entity.getName() );
        teamDto.description( entity.getDescription() );

        return teamDto.build();
    }

    @Override
    public Team toEntity(TeamDto dto) {
        if ( dto == null ) {
            return null;
        }

        Team.TeamBuilder<?, ?> team = Team.builder();

        if ( dto.getStartDate() != null ) {
            team.startDate( LocalDateTime.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        if ( dto.getEndDate() != null ) {
            team.endDate( LocalDateTime.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        team.id( dto.getId() );
        team.active( dto.isActive() );
        team.name( dto.getName() );
        team.description( dto.getDescription() );

        return team.build();
    }

    @Override
    public void partialUpdate(Team entity, TeamDto dto) {
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
    public void update(Team entity, TeamDto dto) {
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
