package com.assignment.interpark.domain.tour;

import com.assignment.interpark.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "city")
public class City extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @Column(nullable = false, length = 100)
    private String cityName;

    @Column(nullable = false)
    @Max(2100000000)
    private int population;

    private LocalDate lastClickDate;

    @Builder
    public City(String cityName, int population) {
        this.cityName = cityName;
        this.population = population;
        this.lastClickDate = LocalDate.now();
    }

    public void cityModify(String cityName, int population) {
        this.cityName = cityName;
        this.population = population;
    }

    public void lastClickDateModify() {
        this.lastClickDate = LocalDate.now();
    }
}
