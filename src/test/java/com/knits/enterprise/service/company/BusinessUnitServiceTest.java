package com.knits.enterprise.service.company;

import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.search.BusinessUnitSearchDto;
import com.knits.enterprise.dto.security.UserDto;
import com.knits.enterprise.mapper.company.BusinessUnitMapper;
import com.knits.enterprise.mapper.company.BusinessUnitMapperImpl;
import com.knits.enterprise.mapper.security.UserMapperImpl;
import com.knits.enterprise.mapper.security.UserMapper;
import com.knits.enterprise.model.BusinessUnitMock;
import com.knits.enterprise.model.company.BusinessUnit;
import com.knits.enterprise.repository.company.BusinessUnitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
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
        BusinessUnitDto toSaveDto = businessUnitMapper.toDto(BusinessUnitMock.createTestBusinessUnitMock(1L));

        when(businessUnitRepository.save(Mockito.any(BusinessUnit.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        BusinessUnitDto savedDto = businessUnitService.saveNewBusinessUnit(toSaveDto);

        verify(businessUnitRepository).save(businessUnitArgumentCaptor.capture());

        verify(userMapper).toEntity(userDtoArgumentCaptor.capture());

        BusinessUnit toSaveEntity = businessUnitArgumentCaptor.getValue();

        UserDto userDto = userDtoArgumentCaptor.getValue();

        verify(businessUnitMapper, times(2)).toDto(toSaveEntity);
        verify(businessUnitMapper, times(1)).toEntity(toSaveDto);
        verify(businessUnitRepository, times(1)).save(toSaveEntity);
        verify(userMapper, times(1)).toEntity(userDto);

        assertThat(toSaveDto.getName()).isEqualTo(savedDto.getName());
        assertThat(toSaveDto.getDescription()).isEqualTo(savedDto.getDescription());
        assertThat(savedDto.isActive()).isTrue();
    }

    @Test
    @DisplayName("partial update success")
    public void partialUpdate(){
        Long entityIdToUpdate = 1L;
        String updateOnBusinessUnitName = "Train to heaven";

        BusinessUnit foundEntity = BusinessUnitMock.createTestBusinessUnitMock(entityIdToUpdate);

        BusinessUnitDto toUpdateDto = businessUnitMapper.toDto(foundEntity);

        toUpdateDto.setName(updateOnBusinessUnitName);

        when(businessUnitRepository.findById(entityIdToUpdate)).thenReturn(Optional.of(foundEntity));

        BusinessUnitDto updatedDto = businessUnitService.partialUpdate(toUpdateDto);

        verify(businessUnitRepository).save(businessUnitArgumentCaptor.capture());

        BusinessUnit businessUnit = businessUnitArgumentCaptor.getValue();

        verify(businessUnitRepository, times(1)).save(businessUnit);
        verify(businessUnitMapper, times(1)).partialUpdate(foundEntity, toUpdateDto);
        verify(businessUnitMapper, times(2)).toDto(foundEntity);

        assertThat(toUpdateDto).isEqualTo(updatedDto);

    }

    @Test
    @DisplayName("deactivation success")
    void deleteSuccess (){
        Long entityIdToDelete = 1L;
        BusinessUnit entityToDelete = BusinessUnitMock.createTestBusinessUnitMock(entityIdToDelete);
        when(businessUnitRepository.findById(entityIdToDelete)).thenReturn(Optional.of(entityToDelete));
        businessUnitService.deactivateBusinessUnit(entityIdToDelete);
        verify(businessUnitRepository, times(1)).findById(entityIdToDelete);
        verify(businessUnitRepository, times(  1)).delete(entityToDelete);
        verify(businessUnitMapper, times(1)).toDto(entityToDelete);
    }



    @Test
    @DisplayName("findAll / pagination success")
    void findAllSuccess (){
        int expectedSize=10;
        Page<BusinessUnit> resultSet = BusinessUnitMock.createTestSetOfBusinessUnitMock(expectedSize);
        BusinessUnitSearchDto businessUnitSearchDto = BusinessUnitMock.createBusinessUnitSearchDto(expectedSize);
        when(businessUnitRepository.findAll((Specification<BusinessUnit>) any(), (Pageable) any())).thenReturn(resultSet);

        PaginatedResponseDto<BusinessUnitDto> businessUnitDtoPaginatedResponseDto = businessUnitService.search(businessUnitSearchDto);
        verify(businessUnitMapper, times(expectedSize)).toDto(any(BusinessUnit.class));
        assertThat(businessUnitDtoPaginatedResponseDto.getData().size()).isEqualTo(expectedSize);
    }

    @Test
    @DisplayName("delete success")
    void deleteByIdSuccess (){
        Long entityId = 1L;
        BusinessUnit savedEntity = BusinessUnitMock.createTestBusinessUnitMock(entityId);
        when(businessUnitRepository.findAllBusinessUnits()).thenReturn(Collections.singletonList(savedEntity));
        businessUnitService.deleteBusinessUnit(entityId);
        verify(businessUnitRepository,times(1)).deleteBusinessUnitById(entityId);
    }

    @Test
    @DisplayName("findById success")
    void findByIdSuccess(){
        Long entityId = 1L;
        BusinessUnit savedEntity = BusinessUnitMock.createTestBusinessUnitMock(entityId);
        when(businessUnitRepository.findById(entityId)).thenReturn(Optional.of(savedEntity));
        businessUnitService.findBusinessUnitById(entityId);
        verify(businessUnitRepository,times(1)).findById(entityId);
    }


}
