package com.knits.enterprise.service.company;

import com.knits.enterprise.config.Constants;
import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.company.EmployeeDto;
import com.knits.enterprise.dto.search.GenericSearchDto;
import com.knits.enterprise.dto.security.UserDto;
import com.knits.enterprise.mapper.company.BusinessUnitMapper;
import com.knits.enterprise.mapper.company.BusinessUnitMapperImpl;
import com.knits.enterprise.mapper.security.UserMapperImpl;
import com.knits.enterprise.mapper.security.UserMapper;
import com.knits.enterprise.model.BusinessUnitMock;
import com.knits.enterprise.model.company.BusinessUnit;
import com.knits.enterprise.model.company.Employee;
import com.knits.enterprise.model.security.User;
import com.knits.enterprise.repository.company.BusinessUnitRepository;
import com.knits.enterprise.repository.security.UserRepository;
import com.knits.enterprise.service.security.UserService;
import jdk.jfr.Description;
import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BusinessUnitServiceTest {
    @Spy
    private BusinessUnitMapper businessUnitMapper = new BusinessUnitMapperImpl();

    @Spy
    private UserMapper userMapper = new UserMapperImpl();

    @Mock
    private BusinessUnitRepository businessUnitRepository;


    @Captor
    private ArgumentCaptor<BusinessUnit> businessUnitArgumentCaptor;

    @Captor
    private ArgumentCaptor<UserDto> userDtoArgumentCaptor;

    @InjectMocks
    private BusinessUnitService businessUnitService;

    @Test
    @DisplayName("Save business unit successful")
    public void saveNewBusinessUnit(){
        BusinessUnitDto toSaveDto = BusinessUnitDto.builder()
                .id(null)
                .startDate("20/10/2020 10:10:10")
                .name("Mocked businessUnitName")
                .description("Mocked description")
                .endDate(null)
                .active(false)
                .createdBy(null)
                .build();

        when(businessUnitRepository.save(Mockito.any(BusinessUnit.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        BusinessUnitDto savedDto = businessUnitService.saveNewBusinessUnit(toSaveDto);

        verify(businessUnitRepository).save(businessUnitArgumentCaptor.capture());

        verify(userMapper).toEntity(userDtoArgumentCaptor.capture());

        BusinessUnit toSaveEntity = businessUnitArgumentCaptor.getValue();

        UserDto userDto = userDtoArgumentCaptor.getValue();

        verify(businessUnitMapper, times(1)).toDto(toSaveEntity);
        verify(businessUnitMapper, times(1)).toEntity(toSaveDto);
        verify(businessUnitRepository, times(1)).save(toSaveEntity);
        verify(userMapper, times(1)).toEntity(userDto);

        assertThat(toSaveDto.getName()).isEqualTo(savedDto.getName());
        assertThat(toSaveDto.getDescription()).isEqualTo(savedDto.getDescription());
        assertThat(toSaveDto.getStartDate()).isNotEqualTo(savedDto.getStartDate());
        assertThat(savedDto.isActive()).isTrue();
    }

    @Test
    @DisplayName("partial update success")
    public void partialUpdate(){
        Long entityIdToUpdate = 1L;
        String updateOnBusinessUnitName = "Train to heaven";

        BusinessUnit foundEntity = BusinessUnitMock.shallowBusinessUnit(entityIdToUpdate);

        BusinessUnitDto toUpdateDto = businessUnitMapper.toDto(foundEntity);

        toUpdateDto.setName(updateOnBusinessUnitName);

        when(businessUnitRepository.findById(entityIdToUpdate)).thenReturn(Optional.of(foundEntity));

        BusinessUnitDto updatedDto = businessUnitService.partialUpdate(entityIdToUpdate, toUpdateDto);

        verify(businessUnitRepository).save(businessUnitArgumentCaptor.capture());

        BusinessUnit businessUnit = businessUnitArgumentCaptor.getValue();

        verify(businessUnitRepository, times(1)).save(businessUnit);
        verify(businessUnitMapper, times(1)).partialUpdate(foundEntity, toUpdateDto);
        verify(businessUnitMapper, times(2)).toDto(foundEntity);

        assertThat(toUpdateDto).isEqualTo(updatedDto);

    }

    @Test
    @DisplayName("delete success")
    void deleteSuccess (){
        Long entityIdToDelete = 1L;
        BusinessUnit entityToDelete = BusinessUnitMock.shallowBusinessUnit(entityIdToDelete);
        when(businessUnitRepository.findById(entityIdToDelete)).thenReturn(Optional.of(entityToDelete));
        doAnswer(invocation -> {
            BusinessUnit businessUnit = invocation.getArgument(0);
            businessUnit.setActive(false);
            return null; // For void methods
        }).when(businessUnitRepository).delete(entityToDelete);
        businessUnitService.deactivateBusinessUnit(entityIdToDelete);
        verify(businessUnitRepository, (times(1))).delete(entityToDelete);
        verify(businessUnitMapper, times(1)).toDto(entityToDelete);
        Assertions.assertEquals(false, entityToDelete.isActive());
    }

    @Test
    public void testDeactivateBusinessUnit() {
        Long entityIdToDelete = 1L;
        BusinessUnit entityToDelete = BusinessUnitMock.shallowBusinessUnit(entityIdToDelete);

        when(businessUnitRepository.findById(entityIdToDelete)).thenReturn(Optional.of(entityToDelete));

        //when( somehow call save method ).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        businessUnitService.deactivateBusinessUnit(1L);

        verify(businessUnitRepository).save(businessUnitArgumentCaptor.capture());

        BusinessUnit result = businessUnitArgumentCaptor.getValue();

        // Verify that the entity has been deactivated
        Assertions.assertFalse(result.isActive());
    }

    @Test
    @DisplayName("findAll / pagination success")
    void findAllSuccess (){
        int expectedSize=10;
        Page<BusinessUnit> resultSet = BusinessUnitMock.shallowPageOfBusinessUnits(expectedSize);
        GenericSearchDto<BusinessUnit> businessUnitSearchDto = BusinessUnitMock.createBusinessUnitSearchDto(expectedSize);
        //when(businessUnitRepository.findAll(businessUnitSearchDto.getSpecification(), businessUnitSearchDto.getPageable())).thenReturn(resultSet);
        doReturn(resultSet).when(businessUnitRepository).findAll((Specification<BusinessUnit>) any(), (Pageable) any());

        PaginatedResponseDto<BusinessUnitDto> businessUnitDtoPaginatedResponseDto = businessUnitService.listAll(businessUnitSearchDto);
        verify(businessUnitMapper, times(expectedSize)).toDto(any(BusinessUnit.class));
        assertThat(businessUnitDtoPaginatedResponseDto.getData().size()).isEqualTo(expectedSize);
    }


}
