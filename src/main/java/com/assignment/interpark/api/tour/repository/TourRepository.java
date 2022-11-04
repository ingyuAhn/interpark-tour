package com.assignment.interpark.api.tour.repository;

import com.assignment.interpark.domain.tour.City;
import com.assignment.interpark.domain.tour.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long>, TourQueryRepository {

    List<Tour> findByCity(City city);
}
