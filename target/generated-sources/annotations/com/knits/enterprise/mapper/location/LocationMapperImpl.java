package com.knits.enterprise.mapper.location;

import com.knits.enterprise.dto.location.LocationDto;
import com.knits.enterprise.mapper.common.AddressMapper;
import com.knits.enterprise.model.enums.OwnershipType;
import com.knits.enterprise.model.location.Location;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T23:37:40+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class LocationMapperImpl implements LocationMapper {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Location toEntity(LocationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Location.LocationBuilder<?, ?> location = Location.builder();

        location.id( dto.getId() );
        location.address( addressMapper.toEntity( dto.getAddress() ) );
        if ( dto.getOwnership() != null ) {
            location.ownership( Enum.valueOf( OwnershipType.class, dto.getOwnership() ) );
        }
        location.mapCoordinates( dto.isMapCoordinates() );
        location.latitude( dto.getLatitude() );
        location.longitude( dto.getLongitude() );

        return location.build();
    }

    @Override
    public List<Location> toEntities(List<LocationDto> entityList) {
        if ( entityList == null ) {
            return new ArrayList<Location>();
        }

        List<Location> list = new ArrayList<Location>( entityList.size() );
        for ( LocationDto locationDto : entityList ) {
            list.add( toEntity( locationDto ) );
        }

        return list;
    }

    @Override
    public LocationDto toDto(Location entity) {
        if ( entity == null ) {
            return null;
        }

        LocationDto.LocationDtoBuilder locationDto = LocationDto.builder();

        locationDto.id( entity.getId() );
        locationDto.address( addressMapper.toDto( entity.getAddress() ) );
        if ( entity.getOwnership() != null ) {
            locationDto.ownership( entity.getOwnership().name() );
        }
        locationDto.mapCoordinates( entity.isMapCoordinates() );
        locationDto.latitude( entity.getLatitude() );
        locationDto.longitude( entity.getLongitude() );

        return locationDto.build();
    }

    @Override
    public List<LocationDto> toDtos(List<Location> entityList) {
        if ( entityList == null ) {
            return new ArrayList<LocationDto>();
        }

        List<LocationDto> list = new ArrayList<LocationDto>( entityList.size() );
        for ( Location location : entityList ) {
            list.add( toDto( location ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Location entity, LocationDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getAddress() != null ) {
            entity.setAddress( addressMapper.toEntity( dto.getAddress() ) );
        }
        if ( dto.getOwnership() != null ) {
            entity.setOwnership( Enum.valueOf( OwnershipType.class, dto.getOwnership() ) );
        }
        entity.setMapCoordinates( dto.isMapCoordinates() );
        if ( dto.getLatitude() != null ) {
            entity.setLatitude( dto.getLatitude() );
        }
        if ( dto.getLongitude() != null ) {
            entity.setLongitude( dto.getLongitude() );
        }
    }

    @Override
    public void update(Location entity, LocationDto dto) {
        if ( dto == null ) {
            return;
        }

        entity.setId( dto.getId() );
        entity.setAddress( addressMapper.toEntity( dto.getAddress() ) );
        if ( dto.getOwnership() != null ) {
            entity.setOwnership( Enum.valueOf( OwnershipType.class, dto.getOwnership() ) );
        }
        else {
            entity.setOwnership( null );
        }
        entity.setMapCoordinates( dto.isMapCoordinates() );
        entity.setLatitude( dto.getLatitude() );
        entity.setLongitude( dto.getLongitude() );
    }
}
