package com.assignment.interpark.api.tour.repository.impl;

import com.assignment.interpark.api.tour.repository.TourQueryRepository;
import com.assignment.interpark.domain.tour.Tour;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.assignment.interpark.domain.tour.QCity.city;
import static com.assignment.interpark.domain.tour.QTour.tour;

public class TourQueryRepositoryImpl extends QuerydslRepositorySupport implements TourQueryRepository {

    public TourQueryRepositoryImpl() {
        super(Tour.class);
    }

    @Override
    public List<Tour> findByTourQuery() {

        NumberExpression<Integer> roleRank = new CaseBuilder()
                .when(tour.startData.between(LocalDate.now(), LocalDate.now().plusDays(3) )).then(1)
                .when(tour.city.createDate.between(LocalDateTime.now().minusDays(1), LocalDateTime.now() )).then(2)
                .when(tour.city.lastClickDate.between(LocalDate.now().minusDays(7), LocalDate.now() )).then(3)
                .otherwise(4);

        return from(tour)
                .leftJoin(city)
                .on(tour.city.cityId.eq(city.cityId))
                .where(tour.endDate.after(LocalDate.now()))
                .orderBy(roleRank.desc())
                .fetch();

    }
}
