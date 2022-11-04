package com.assignment.interpark.api.city.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {

    @NotBlank(message = "이름이 입력되지 않았습니다.")
    private String cityName;

    @NotNull(message = "인구수가 입력되지 않았습니다.")
    @Min(value = 100, message = "최소값은 100입니다.")
    @Max(value = 2100000000, message = "int Type 2,100,000,000")
    private int population;

}
