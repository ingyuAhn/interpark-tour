package com.assignment.interpark.api.city.dto.response;


import com.assignment.interpark.api.tour.dto.response.TourResponse;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class CityResponse {

    private String cityName;

    private int population;

    private List<TourResponse> tourResponses;

    public CityResponse(String cityName, int population, List<TourResponse> tourResponses) {
        this.cityName = cityName;
        this.population = population;
        this.tourResponses = tourResponses;
    }

    public CityResponse(String cityName, int population) {
        this.cityName = cityName;
        this.population = population;
    }
}
