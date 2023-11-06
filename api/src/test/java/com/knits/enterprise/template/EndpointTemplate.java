package com.knits.enterprise.template;

import com.knits.enterprise.dto.security.UserDto;
import com.knits.enterprise.Constants.EnvVars;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public abstract class EndpointTemplate {

    protected String getBaseUrl() {
        return EnvVars.BASE_URL;
    }

    protected abstract String getEndpoint();

    protected UserDto getCurrentUser() {
        return UserDto.builder()
                .login(EnvVars.TEST_USERNAME)
                .password(EnvVars.TEST_PASSWORD)
                .build();
    }

    public void delete(String token, Long id) {
        Response response = httpDelete(token, id, HttpStatus.NO_CONTENT.value());
    }

    protected String getEndpointUrl() {
        System.out.println(getBaseUrl() + "/" + getEndpoint());
        return getBaseUrl() + "/" + getEndpoint();
    }

    public <T> Response httpPost(String token, T data, int expectedHttpCode) {

        RequestSpecification request = setupRequest(token, data);
        Response response = request.post(getEndpointUrl());
        assertThat(response.getStatusCode()).isEqualTo(expectedHttpCode);
        return response;
    }

    protected <T> Response httpPut(String token, T data, int expectedHttpCode) {

        RequestSpecification request = setupRequest(token, data);
        Response response = request.put(getEndpointUrl());
        assertThat(response.getStatusCode()).isEqualTo(expectedHttpCode);
        return response;
    }



    protected <T> Response httpPatch(String token, T data, int expectedHttpCode) {

        RequestSpecification request = setupRequest(token, data);
        Response response = request.patch(getEndpointUrl());
        assertThat(response.getStatusCode()).isEqualTo(expectedHttpCode);
        return response;
    }

    protected <T> Response httpDelete(String token, Long id, int expectedCode) {

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        Response response = request.delete(getEndpointUrl() + "/" + id);
        assertThat(response.getStatusCode()).isEqualTo(expectedCode);
        return response;
    }

    protected <T> Response httpGetPathParams(String token, String pathParams, int expectedHttpCode) {

        RequestSpecification request = setupRequest(token);

        Response response = StringUtils.isNotEmpty(pathParams) ?
                request.get(getEndpointUrl() + "/" + pathParams) :
                request.get(getEndpointUrl());

        assertThat(response.getStatusCode()).isEqualTo(200);
        return response;
    }

    protected <T> Response httpPutPathParams(String token, String pathParams, int expectedHttpCode) {

        RequestSpecification request = setupRequest(token);

        Response response = StringUtils.isNotEmpty(pathParams) ?
                request.put(getEndpointUrl() + "/" + pathParams) :
                request.put(getEndpointUrl());

        assertThat(response.getStatusCode()).isEqualTo(expectedHttpCode);
        return response;
    }

    protected <T> Response httpGetQueryString(String token, String queryString, int expectedHttpCode) {

        RequestSpecification request = setupRequest(token);
        getBaseUrl();
        Response response = StringUtils.isNotEmpty(queryString) ?
                request.get(getEndpointUrl() + "?" + queryString) :
                request.get(getEndpointUrl());
        assertThat(response.getStatusCode()).isEqualTo(200);
        return response;
    }

    private <T> RequestSpecification setupRequest(String token, T data) {
        RequestSpecification request = setupRequest(token);
        request.body(data);
        return request;
    }

    private RequestSpecification setupRequest(String token) {
        return StringUtils.isEmpty(token) ? unAuthorizedRequest() : authorizedRequest(token);
    }

    private RequestSpecification authorizedRequest(String token) {
        RequestSpecification request = unAuthorizedRequest();
        request.header("Authorization", "Bearer " + token);
        return request;
    }

    private RequestSpecification unAuthorizedRequest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        return request;
    }
}
