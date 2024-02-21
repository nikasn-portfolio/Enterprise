package com.knits.enterprise.mapper.company;

import com.knits.enterprise.dto.company.DepartmentDto;
import com.knits.enterprise.model.company.Department;
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
public class DepartmentMapperImpl implements DepartmentMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 = DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" );

    @Override
    public List<Department> toEntities(List<DepartmentDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Department>();
        }

        List<Department> list = new ArrayList<Department>( entityList.size() );
        for ( DepartmentDto departmentDto : entityList ) {
            list.add( toEntity( departmentDto ) );
        }

        return list;
    }

    @Override
    public List<DepartmentDto> toDtos(List<Department> entityList) {
        if ( entityList == null ) {
            return new ArrayList<DepartmentDto>();
        }

        List<DepartmentDto> list = new ArrayList<DepartmentDto>( entityList.size() );
        for ( Department department : entityList ) {
            list.add( toDto( department ) );
        }

        return list;
    }

    @Override
    public DepartmentDto toDto(Department entity) {
        if ( entity == null ) {
            return null;
        }

        DepartmentDto.DepartmentDtoBuilder<?, ?> departmentDto = DepartmentDto.builder();

        if ( entity.getStartDate() != null ) {
            departmentDto.startDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getStartDate() ) );
        }
        if ( entity.getEndDate() != null ) {
            departmentDto.endDate( dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904.format( entity.getEndDate() ) );
        }
        departmentDto.active( entity.isActive() );
        departmentDto.id( entity.getId() );
        departmentDto.name( entity.getName() );
        departmentDto.description( entity.getDescription() );

        return departmentDto.build();
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Department.DepartmentBuilder<?, ?> department = Department.builder();

        if ( dto.getStartDate() != null ) {
            department.startDate( LocalDateTime.parse( dto.getStartDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        if ( dto.getEndDate() != null ) {
            department.endDate( LocalDateTime.parse( dto.getEndDate(), dateTimeFormatter_dd_MM_yyyy_HH_mm_ss_02049711904 ) );
        }
        department.id( dto.getId() );
        department.active( dto.isActive() );
        department.name( dto.getName() );
        department.description( dto.getDescription() );

        return department.build();
    }

    @Override
    public void partialUpdate(Department entity, DepartmentDto dto) {
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
    public void update(Department entity, DepartmentDto dto) {
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
