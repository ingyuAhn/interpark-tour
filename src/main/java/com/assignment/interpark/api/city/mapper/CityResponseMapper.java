package com.assignment.interpark.api.city.mapper;

import com.assignment.interpark.api.city.dto.response.CityResponse;
import com.assignment.interpark.common.mapper.GenericMapper;
import com.assignment.interpark.domain.tour.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityResponseMapper {

    CityResponse cityResponseDto(City city);
}
