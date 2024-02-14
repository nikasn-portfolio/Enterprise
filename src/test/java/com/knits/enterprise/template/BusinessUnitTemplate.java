package com.knits.enterprise.template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knits.enterprise.dto.common.PaginatedResponseDto;
import com.knits.enterprise.dto.company.BusinessUnitDto;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;


public class BusinessUnitTemplate extends EndpointTemplate {

    public BusinessUnitDto create(String token, BusinessUnitDto expected) {
        Response response = httpPost(token, expected, HttpStatus.CREATED.value());
        BusinessUnitDto actual = response.getBody().as(BusinessUnitDto.class);
        assertThat(actual).usingRecursiveComparison().ignoringFields("id","startDate","endDate").isEqualTo(expected);
        return actual;
    }

    public int deactivate(String token, Long id) {
        Response response = httpPutPathParams(token, String.valueOf(id), HttpStatus.NO_CONTENT.value());
        return response.getStatusCode();
    }

    public BusinessUnitDto findById(String token, Long id) {
        Response response = httpGetPathParams(token, String.valueOf(id), HttpStatus.OK.value());
        return response.getBody().as(BusinessUnitDto.class);
    }

    public BusinessUnitDto partialUpdate(String token, BusinessUnitDto expected) {
        Response response = httpPatch(token, expected, HttpStatus.OK.value());
        BusinessUnitDto actual = response.getBody().as(BusinessUnitDto.class);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        return actual;
    }

    public PaginatedResponseDto<BusinessUnitDto> search(String token, String queryString) throws JsonProcessingException {
        Response response = httpGetQueryString(token, queryString, HttpStatus.OK.value());
        TypeReference<PaginatedResponseDto<BusinessUnitDto>> typeRef = new TypeReference<PaginatedResponseDto<BusinessUnitDto>>() {};
        ObjectMapper om = new ObjectMapper();
        return om.readValue(response.getBody().asString(), typeRef);
    }

    @Override
    protected String getEndpoint() {
        return "api/businessUnits";
    }


}
