package com.assignment.interpark.api.city.repository.impl;

import com.assignment.interpark.api.city.repository.CityQueryRepository;
import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.domain.tour.City;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class CityQueryRepositoryImpl extends QuerydslRepositorySupport implements CityQueryRepository{

    public CityQueryRepositoryImpl() {
        super(City.class);
    }
    @Override
    public List<TourResponse> findCityQuery() {
        return null;
    }
}
