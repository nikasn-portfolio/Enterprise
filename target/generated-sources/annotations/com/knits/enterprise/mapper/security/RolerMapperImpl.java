package com.knits.enterprise.mapper.security;

import com.knits.enterprise.dto.security.RoleDto;
import com.knits.enterprise.model.security.Role;
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
public class RolerMapperImpl implements RolerMapper {

    @Override
    public Role toEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role.RoleBuilder<?, ?> role = Role.builder();

        role.id( dto.getId() );
        role.name( dto.getName() );

        return role.build();
    }

    @Override
    public List<Role> toEntities(List<RoleDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Role>();
        }

        List<Role> list = new ArrayList<Role>( entityList.size() );
        for ( RoleDto roleDto : entityList ) {
            list.add( toEntity( roleDto ) );
        }

        return list;
    }

    @Override
    public RoleDto toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto.RoleDtoBuilder<?, ?> roleDto = RoleDto.builder();

        return roleDto.build();
    }

    @Override
    public List<RoleDto> toDtos(List<Role> entityList) {
        if ( entityList == null ) {
            return new ArrayList<RoleDto>();
        }

        List<RoleDto> list = new ArrayList<RoleDto>( entityList.size() );
        for ( Role role : entityList ) {
            list.add( toDto( role ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Role entity, RoleDto dto) {
        if ( dto == null ) {
            return;
        }
    }

    @Override
    public void update(Role entity, RoleDto dto) {
        if ( dto == null ) {
            return;
        }
    }
}
