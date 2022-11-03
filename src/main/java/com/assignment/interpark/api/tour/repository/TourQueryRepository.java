package com.assignment.interpark.api.tour.repository;

import com.assignment.interpark.domain.tour.Tour;

import java.util.List;

public interface TourQueryRepository {

    List<Tour> findByTourQuery();
}
