package com.assignment.interpark.api.tour.repository.impl;

import com.assignment.interpark.api.tour.repository.TourQueryRepository;
import static com.assignment.interpark.domain.tour.QTour.tour;
import com.assignment.interpark.domain.tour.Tour;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class TourQueryRepositoryImpl extends QuerydslRepositorySupport implements TourQueryRepository {

    public TourQueryRepositoryImpl() {
        super(Tour.class);
    }

    @Override
    public List<Tour> findByTourQuery() {
        return from(tour).leftJoin(tour.city)
                .orderBy(tour.startData.desc()).fetch();
    }
}
