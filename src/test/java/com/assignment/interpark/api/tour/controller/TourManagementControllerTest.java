package com.assignment.interpark.api.tour.controller;

import com.assignment.interpark.api.city.dto.request.CityRequest;
import com.assignment.interpark.api.city.repository.CityRepository;
import com.assignment.interpark.api.tour.dto.request.TourRequest;
import com.assignment.interpark.domain.tour.City;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TourManagementControllerTest {


    @Autowired
    private CityRepository cityRepository;

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        City city = new City("LA", 3840000);
        cityRepository.save(city);
    }

    @Test
    @DisplayName("여행관련API 테스트를한다")
    void createTour() {
        
        TourRequest tourRequest = new TourRequest(Long.valueOf(1), LocalDate.now().plusDays(1), LocalDate.now().plusDays(3));

        /*
         * 여행 생성 API
         * */
        ExtractableResponse<Response> createResponse = RestAssured.given().log().all()
                .body(tourRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/tour/management")
                .then().log().all()
                .extract();

        assertEquals(createResponse.statusCode(), HttpStatus.OK.value());

        /*
         * 여행 수정 API
         * */
        TourRequest tourModifyRequest = new TourRequest(Long.valueOf(1), LocalDate.now().plusDays(1), LocalDate.now().plusDays(7));
        ExtractableResponse<Response> modifyResponse = RestAssured.given().log().all()
                .body(tourModifyRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("/tour/management/1")
                .then().log().all()
                .extract();

        assertEquals(modifyResponse.statusCode(), HttpStatus.OK.value());

        /*
         * 여행 단일조회 API
         * */
        ExtractableResponse<Response> findOneResponse = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/tour/management/1")
                .then().log().all()
                .extract();

        assertEquals(findOneResponse.statusCode(), HttpStatus.OK.value());

        ExtractableResponse<Response> DeleteCity = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/tour/management/1")
                .then().log().all()
                .extract();

        assertEquals(DeleteCity.statusCode(), HttpStatus.OK.value());
    }
}