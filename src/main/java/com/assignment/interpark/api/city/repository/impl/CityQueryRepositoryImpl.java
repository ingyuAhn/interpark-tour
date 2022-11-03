package com.assignment.interpark.api.city.repository.impl;

import com.assignment.interpark.api.city.repository.CityQueryRepository;
import com.assignment.interpark.config.database.QuerydslConfig;
import com.assignment.interpark.domain.tour.City;
import static com.assignment.interpark.domain.tour.QCity.city;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CityQueryRepositoryImpl extends QuerydslRepositorySupport implements CityQueryRepository{

    public CityQueryRepositoryImpl() {
        super(City.class);
    }
    @Override
    public List<City> findCityQuery() {
        return from(city).fetch();
    }
}
