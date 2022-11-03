package com.assignment.interpark.api.city.repository;

import com.assignment.interpark.domain.tour.City;

import java.util.List;

public interface CityQueryRepository {

    List<City> findCityQuery();
}
