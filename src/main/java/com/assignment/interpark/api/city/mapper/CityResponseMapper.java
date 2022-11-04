package com.assignment.interpark.api.city.mapper;

import com.assignment.interpark.api.city.dto.response.CityResponse;
import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.domain.tour.City;
import com.assignment.interpark.domain.tour.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.annotation.Nullable;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CityResponseMapper {

    @Mapping(source = "tourList", target = "tourResponses")
    CityResponse cityOrTourListResponseDto(City city, @Nullable List<Tour> tourList);

    List<CityResponse> cityResponseListDto(List<City> cityList);
    
    List<TourResponse> tourResponseListDto(List<Tour> tourList);

    @Mapping(source = "city.cityName", target = "cityName")
    @Mapping(source = "city.cityId", target = "cityId")
    TourResponse tourToTourResponse(Tour tour);

}
