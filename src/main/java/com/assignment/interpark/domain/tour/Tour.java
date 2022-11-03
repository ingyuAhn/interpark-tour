package com.assignment.interpark.domain.tour;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tour")
public class Tour {

    @Id
    private Long tourId;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    @Column(nullable = false)
    private LocalDateTime startData;

    @Column(nullable = false)
    private LocalDateTime endDate;

}
