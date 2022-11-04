package com.assignment.interpark.api.tour.dto.response;

import com.assignment.interpark.api.city.dto.response.CityResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Setter
public class TourResponse {

    private LocalDate startData;

    private LocalDate endDate;

    private String cityName;

    private Long cityId;

    public TourResponse(LocalDate startData, LocalDate endDate, String cityName, Long cityId) {
        this.startData = startData;
        this.endDate = endDate;
        this.cityName = cityName;
        this.cityId = cityId;
    }
}
