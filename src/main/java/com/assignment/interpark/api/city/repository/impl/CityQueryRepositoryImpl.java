package com.assignment.interpark.api.city.repository.impl;

import com.assignment.interpark.api.city.repository.CityQueryRepository;
import com.assignment.interpark.domain.tour.City;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.assignment.interpark.domain.tour.QCity.city;
import static com.assignment.interpark.domain.tour.QTour.tour;


public class CityQueryRepositoryImpl extends QuerydslRepositorySupport implements CityQueryRepository{

    public CityQueryRepositoryImpl() {
        super(City.class);
    }
    @Override
    public List<City> findCityQuery() {
        return null;
    }
}
