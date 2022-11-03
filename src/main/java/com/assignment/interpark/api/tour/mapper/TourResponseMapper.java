package com.assignment.interpark.api.tour.mapper;

import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.domain.tour.City;
import com.assignment.interpark.domain.tour.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourResponseMapper {

    @Mapping(source = "city.cityName", target = "cityResponse.cityName")
    TourResponse tourResponseDto(Tour tour);
}
