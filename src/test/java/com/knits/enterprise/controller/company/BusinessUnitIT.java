package com.knits.enterprise.controller.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import com.knits.enterprise.dto.BusinessUnitDtoMock;
import com.knits.enterprise.template.BusinessUnitTemplate;
import com.knits.enterprise.template.UserTemplate;
import com.knits.enterprise.utils.TestConsts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
public class BusinessUnitIT{

    private BusinessUnitTemplate businessUnitTemplate;

    private UserTemplate userTemplate;

    @BeforeEach
    public void init(){
        businessUnitTemplate = new BusinessUnitTemplate();
        userTemplate = new UserTemplate();
    }

    private static final String MOCK_NAME="MockBusinessUnitNameItest";
    private static final String MOCK_UPDATED_NAME="MockBusinessUnitNameItest-Update";
    @Test
    public void testCreateBusinessUnitSuccess () throws Exception{
        String token =userTemplate.loginAndGetToken();
        BusinessUnitDto saved =createFlow(token,MOCK_NAME);

        BusinessUnitDto foundById =businessUnitTemplate.findById(token,saved.getId());
        assertThat(foundById).usingRecursiveComparison().isEqualTo(saved);

        businessUnitTemplate.delete(token,foundById.getId());
    }

    @Test
    public void testPartialUpdate()throws Exception{
        String token =userTemplate.loginAndGetToken();
        BusinessUnitDto saved =createFlow(token,MOCK_NAME);

        saved.setName(MOCK_UPDATED_NAME);
        BusinessUnitDto updated =businessUnitTemplate.partialUpdate(token,saved);
        assertThat(updated.getName()).isEqualTo(MOCK_UPDATED_NAME);

        BusinessUnitDto foundById =businessUnitTemplate.findById(token,updated.getId());
        assertThat(foundById).usingRecursiveComparison().isEqualTo(updated);

        businessUnitTemplate.delete(token,foundById.getId());
    }

    @Test
    public void testDeactivatesBusinessUnit() throws Exception {
        String token =userTemplate.loginAndGetToken();
        BusinessUnitDto saved =createFlow(token,MOCK_NAME);
        int deactivate = businessUnitTemplate.deactivate(token, saved.getId());
        assertThat(deactivate).isEqualTo(204);
        businessUnitTemplate.delete(token,saved.getId());


    }


    private BusinessUnitDto createFlow(String token, String name) throws Exception{

        BusinessUnitDto expected = BusinessUnitDtoMock.shallowBusinessUnitDto(TestConsts.NO_COUNTER);
        expected.setName(name);
        cleanUpByName(token,expected.getName());
        return businessUnitTemplate.create(token,expected);

    }


    private void cleanUpByName(String token,String name) throws JsonProcessingException {
        PaginatedResponseDto<BusinessUnitDto> response= businessUnitTemplate.search(token,"name="+name);

        if(!response.getData().isEmpty()){
            assertThat(response.getData().size()).isEqualTo(1);
            businessUnitTemplate.delete(token,response.getData().get(0).getId());
        }
    }
}
