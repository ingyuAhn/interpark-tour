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

    private CityResponse cityResponse;

    public TourResponse(LocalDate startDate, LocalDate endDate, CityResponse cityResponse) {
        this.startData = startDate;
        this.endDate = endDate;
        this.cityResponse = cityResponse;
    }
}
