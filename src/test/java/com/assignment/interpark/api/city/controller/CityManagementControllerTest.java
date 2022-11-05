package com.assignment.interpark.api.city.controller;

import com.assignment.interpark.api.city.dto.request.CityRequest;
import com.assignment.interpark.api.city.dto.response.CityResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CityManagementControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("도시관련API 테스트를한다")
    void createCity() {
        CityRequest cityRequest = new CityRequest("서울", 10000000);

        /*
        * 도시 생성 API
        * */
        ExtractableResponse<Response> createResponse = RestAssured.given().log().all()
                .body(cityRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/city/management")
                .then().log().all()
                .extract();

        assertEquals(createResponse.statusCode(), HttpStatus.OK.value());

        /*
         * 도시 수정 API
         * */
        CityRequest cityModifyRequest = new CityRequest("서울", 20000000);
        ExtractableResponse<Response> modifyResponse = RestAssured.given().log().all()
                .body(cityModifyRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("/city/management/1")
                .then().log().all()
                .extract();

        assertEquals(modifyResponse.statusCode(), HttpStatus.OK.value());


        /*
         * 도시 단일조회 API
         * */
        ExtractableResponse<Response> findOneResponse = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/city/management/1")
                .then().log().all()
                .extract();

        assertEquals(findOneResponse.statusCode(), HttpStatus.OK.value());

        /*
         * 도시 리스트조회 API
         * */
        ExtractableResponse<Response> findList = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/city/management")
                .then().log().all()
                .extract();

        assertEquals(findList.statusCode(), HttpStatus.OK.value());

        ExtractableResponse<Response> DeleteCity = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/city/management/1")
                .then().log().all()
                .extract();

        assertEquals(DeleteCity.statusCode(), HttpStatus.OK.value());

    }


}