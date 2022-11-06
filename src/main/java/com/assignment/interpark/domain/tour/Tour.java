package com.assignment.interpark.domain.tour;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourId;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Builder
    public Tour(City city, LocalDate startDate, LocalDate endDate) {
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void tourModify(City city, LocalDate startDate, LocalDate endDate) {
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
