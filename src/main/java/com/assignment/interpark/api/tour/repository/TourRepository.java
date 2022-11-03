package com.assignment.interpark.api.tour.repository;

import com.assignment.interpark.domain.tour.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long>, TourQueryRepository {
}
