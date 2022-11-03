package com.assignment.interpark.api.city.dto.response;


import lombok.*;

@Getter
@NoArgsConstructor
@Setter
public class CityResponse {

    private String cityName;

    private int population;

    public CityResponse(String cityName, int population) {
        this.cityName = cityName;
        this.population = population;
    }
}
