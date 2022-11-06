package com.assignment.interpark.api.city.repository;

import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.domain.tour.City;

import java.util.List;

public interface CityQueryRepository {

    List<TourResponse> findCityQuery();
}
