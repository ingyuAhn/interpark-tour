package com.assignment.interpark.api.tour.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourRequest {

    @NotNull(message = "도시를 입력하세")
    private Long cityId;

    @Future
    @NotNull(message = "시작일자를 입력하세요")
    private LocalDate startDate;

    @Future
    @NotNull(message = "종료일를 입력하세요")
    private LocalDate endDate;

}
