package com.assignment.interpark.api.city.repository;

import com.assignment.interpark.domain.tour.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long>, CityQueryRepository{

    Optional<City> findByCityName(String cityName);
}
