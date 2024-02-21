package com.knits.enterprise.mapper.company;

import com.knits.enterprise.dto.company.GroupDto;
import com.knits.enterprise.model.company.Group;
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
public class GroupMapperImpl implements GroupMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 = DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" );

    @Override
    public List<Group> toEntities(List<GroupDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Group>();
        }

        List<Group> list = new ArrayList<Group>( entityList.size() );
        for ( GroupDto groupDto : entityList ) {
            list.add( toEntity( groupDto ) );
        }

        return list;
    }

    @Override
    public List<GroupDto> toDtos(List<Group> entityList) {
        if ( entityList == null ) {
            return new ArrayList<GroupDto>();
        }

        List<GroupDto> list = new ArrayList<GroupDto>( entityList.size() );
        for ( Group group : entityList ) {
            list.add( toDto( group ) );
        }

        return list;
    }

    @Override
    public GroupDto toDto(Group entity) {
        if ( entity == null ) {
            return null;
        }

        GroupDto.GroupDtoBuilder<?, ?> groupDto = GroupDto.builder();

        if ( entity.getStartDate() != null ) {
            groupDto.startDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getStartDate() ) );
        }
        if ( entity.getEndDate() != null ) {
            groupDto.endDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getEndDate() ) );
        }
        groupDto.active( entity.isActive() );
        groupDto.id( entity.getId() );
        groupDto.name( entity.getName() );
        groupDto.description( entity.getDescription() );

        return groupDto.build();
    }

    @Override
    public Group toEntity(GroupDto dto) {
        if ( dto == null ) {
            return null;
        }

        Group.GroupBuilder<?, ?> group = Group.builder();

        if ( dto.getStartDate() != null ) {
            group.startDate( LocalDateTime.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        if ( dto.getEndDate() != null ) {
            group.endDate( LocalDateTime.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        group.id( dto.getId() );
        group.active( dto.isActive() );
        group.name( dto.getName() );
        group.description( dto.getDescription() );

        return group.build();
    }

    @Override
    public void partialUpdate(Group entity, GroupDto dto) {
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
    public void update(Group entity, GroupDto dto) {
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
