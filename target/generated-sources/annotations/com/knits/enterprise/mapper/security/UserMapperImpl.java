package com.knits.enterprise.mapper.security;

import com.knits.enterprise.dto.security.UserDto;
import com.knits.enterprise.model.security.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T23:37:39+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.id( dto.getId() );
        user.login( dto.getLogin() );
        user.password( dto.getPassword() );
        user.firstName( dto.getFirstName() );
        user.lastName( dto.getLastName() );
        user.email( dto.getEmail() );
        user.active( dto.getActive() );

        return user.build();
    }

    @Override
    public List<User> toEntities(List<UserDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<User>();
        }

        List<User> list = new ArrayList<User>( entityList.size() );
        for ( UserDto userDto : entityList ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto.UserDtoBuilder<?, ?> userDto = UserDto.builder();

        userDto.id( entity.getId() );
        userDto.login( entity.getLogin() );
        userDto.password( entity.getPassword() );
        userDto.firstName( entity.getFirstName() );
        userDto.lastName( entity.getLastName() );
        userDto.email( entity.getEmail() );
        userDto.active( entity.getActive() );

        return userDto.build();
    }

    @Override
    public List<UserDto> toDtos(List<User> entityList) {
        if ( entityList == null ) {
            return new ArrayList<UserDto>();
        }

        List<UserDto> list = new ArrayList<UserDto>( entityList.size() );
        for ( User user : entityList ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(User entity, UserDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getLogin() != null ) {
            entity.setLogin( dto.getLogin() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getFirstName() != null ) {
            entity.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            entity.setLastName( dto.getLastName() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getActive() != null ) {
            entity.setActive( dto.getActive() );
        }
    }

    @Override
    public void update(User entity, UserDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setLogin( dto.getLogin() );
        entity.setPassword( dto.getPassword() );
        entity.setFirstName( dto.getFirstName() );
        entity.setLastName( dto.getLastName() );
        entity.setEmail( dto.getEmail() );
        entity.setActive( dto.getActive() );
    }
}
