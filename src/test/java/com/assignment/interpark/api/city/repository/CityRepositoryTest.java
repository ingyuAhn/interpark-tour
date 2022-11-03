package com.assignment.interpark.api.city.repository;

import com.assignment.interpark.domain.tour.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    void cityCreate() {
        for (int i=0; i < 10; i++){
            City city = new City("city"+1, 1000+1);
            cityRepository.save(city);
        }
    }

    @Test
    void cityQuery() {
        cityRepository.findCityQuery().forEach(city -> {
            System.out.println("#cityNumber" + city.getCityId());
        });
    }

}