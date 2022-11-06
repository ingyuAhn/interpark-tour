package com.assignment.interpark.api.tour.repository.impl;

import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.api.tour.repository.TourQueryRepository;
import com.assignment.interpark.domain.tour.Tour;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.Expressions;
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
    public List<TourResponse> findByTourQuery() {

        DatePath startDate = Expressions.datePath(LocalDate.class, "startDate");
        DatePath lastClickDate = Expressions.datePath(LocalDate.class, "lastClickDate");
        DatePath cityCreateDate = Expressions.datePath(LocalDate.class, "cityCreateDate");


        return from(tour)
                .select(Projections.fields(TourResponse.class,
                        tour.city.cityName,
                        tour.city.cityId,
                        tour.startDate,
                        tour.endDate,
                        tour.city.lastClickDate,
                        tour.city.createDate.as("cityCreateDate")))
                .leftJoin(city)
                .on(tour.city.cityId.eq(city.cityId))
                .where(tour.endDate.after(LocalDate.now()))
                .fetch();

    }
}
