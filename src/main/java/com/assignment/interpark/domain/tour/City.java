package com.assignment.interpark.domain.tour;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
