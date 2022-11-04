package com.assignment.interpark.api.tour.mapper;

import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.domain.tour.City;
import com.assignment.interpark.domain.tour.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourResponseMapper {

    @Mapping(source = "city.cityName", target = "cityName")
    @Mapping(source = "city.cityId", target = "cityId")
    TourResponse tourResponseDto(Tour tour);
}
