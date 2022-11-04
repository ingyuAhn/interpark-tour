package com.assignment.interpark.api.tour.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TourRepositoryTest {

    @Autowired
    private TourRepository tourRepository;

    @Test
    void tourQueryTest() {
        tourRepository.findByTourQuery();
    }
}