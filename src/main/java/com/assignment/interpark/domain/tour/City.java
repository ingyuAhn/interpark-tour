package com.assignment.interpark.domain.tour;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "city")
public class City {

    @Id
    private Long cityId;

    @Column(nullable = false)
    private String cityName;

    @Column(nullable = false)
    private int population;

    @Builder
    public City(String cityName, int population) {
        this.cityName = cityName;
        this.population = population;
    }

    public void cityModify(String cityName, int population) {
        this.cityName = cityName;
        this.population = population;
    }
}
