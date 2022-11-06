package com.assignment.interpark.api.tour.dto.response;

import com.assignment.interpark.api.city.dto.response.CityResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
public class TourResponse {

    private LocalDate startDate;

    private LocalDate endDate;

    private String cityName;

    private Long cityId;

    private LocalDateTime cityCreateDate;

    private LocalDate lastClickDate;


    public TourResponse(LocalDate startDate, LocalDate endDate, String cityName, Long cityId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cityName = cityName;
        this.cityId = cityId;
    }
}
