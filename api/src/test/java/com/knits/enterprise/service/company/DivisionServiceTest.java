package com.knits.enterprise.service.company;
import com.knits.enterprise.dto.company.DivisionDto;
import com.knits.enterprise.mapper.company.DivisionMapper;
import com.knits.enterprise.mapper.company.DivisionMapperImpl;
import com.knits.enterprise.mocks.dto.DivisionDtoMocks;
import com.knits.enterprise.mocks.model.DivisionMocks;
import com.knits.enterprise.model.company.Division;
import com.knits.enterprise.repository.company.DivisionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DivisionServiceTest {

    @Mock
    private DivisionRepository divisionRepository;

    @Spy
    private DivisionMapper divisionMapper = new DivisionMapperImpl();

    @Captor
    private ArgumentCaptor<Division> divisionArgumentCaptor;

    @InjectMocks
    private DivisionService divisionService;

    @Test
    @DisplayName("Save new Division success")
    void saveNewDivision() {

        DivisionDto toSaveDto = DivisionDtoMocks.shallowDivisionDto(null);

        when(divisionRepository.save(Mockito.any(Division.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);

        DivisionDto savedDto = divisionService.saveNewDivision(toSaveDto);

        verify(divisionRepository).save(divisionArgumentCaptor.capture());
        Division toSaveEntity = divisionArgumentCaptor.getValue();

        verify(divisionMapper, times(1)).toEntity(toSaveDto);
        verify(divisionRepository, times(1)).save(toSaveEntity);
        verify(divisionMapper, times(1)).toDto(toSaveEntity);

        assertThat(toSaveDto).isEqualTo(savedDto);
    }


    @Test
    @DisplayName("Partial update success")
    void partialUpdate() {

        Long entityIdToUpdate = 1L;
        String updateOnDivisionDescription = "Updated mock description";

        Division foundEntity = DivisionMocks.shallowDivision(entityIdToUpdate);
        DivisionDto toUpdateDto = divisionMapper.toDto(foundEntity);
        toUpdateDto.setDescription(updateOnDivisionDescription);

        when(divisionRepository.findById(entityIdToUpdate)).thenReturn(Optional.of(foundEntity));

        DivisionDto updatedDto = divisionService.partialUpdate(toUpdateDto);

        verify(divisionRepository).save(divisionArgumentCaptor.capture());
        Division toUpdateEntity = divisionArgumentCaptor.getValue();

        verify(divisionMapper, times(1)).partialUpdate(foundEntity, toUpdateDto);
        verify(divisionRepository, times(1)).save(foundEntity);
        verify(divisionMapper, times(2)).toDto(foundEntity);

        assertThat(toUpdateDto).isEqualTo(updatedDto);

    }

    @Test
    @DisplayName("Delete success")
    void deleteDivision() {
        Long entityToDelete = 1L;
        divisionService.deleteDivision(entityToDelete);
    }

    @Test
    @DisplayName("findDivisionById Success")
    void findDivisionById() {

        Long entityIdToFind = 1L;
        Division foundEntity = DivisionMocks.shallowDivision(entityIdToFind);

        when(divisionRepository.findById(any(Long.class))).thenReturn(Optional.of(foundEntity));

        var result = divisionService.findDivisionById(entityIdToFind);
        verify(divisionRepository, times(1)).findById(any(Long.class));

        assertNotNull(result);
        assertEquals(foundEntity.getId(), result.getId());


    }
}

























