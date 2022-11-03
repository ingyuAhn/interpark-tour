package com.assignment.interpark.api.tour.service;

import com.assignment.interpark.api.city.repository.CityRepository;
import com.assignment.interpark.api.tour.dto.request.TourRequest;
import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.api.tour.mapper.TourResponseMapper;
import com.assignment.interpark.api.tour.repository.TourRepository;
import com.assignment.interpark.common.message.ResponseDataMessage;
import com.assignment.interpark.common.message.ResponseMessage;
import com.assignment.interpark.domain.tour.City;
import com.assignment.interpark.domain.tour.Tour;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * The type Tour management service.
 */
@Service
public class TourManagementService {

    private final TourRepository tourRepository;
    private final CityRepository cityRepository;
    private final TourResponseMapper tourResponseMapper;

    /**
     * Instantiates a new Tour management service.
     *
     * @param tourRepository     the tour repository
     * @param cityRepository     the city repository
     * @param tourResponseMapper the tour response mapper
     */
    public TourManagementService(TourRepository tourRepository,
                                 CityRepository cityRepository,
                                 TourResponseMapper tourResponseMapper) {
        this.tourRepository = tourRepository;
        this.cityRepository = cityRepository;
        this.tourResponseMapper = tourResponseMapper;
    }

    /**
     * Tour produce process response message.
     *
     * @param tourRequest the tour request
     * @return the response message
     */
    @Transactional
    public ResponseMessage tourProduceProcess(TourRequest tourRequest) {
        try{
            localDateCompareTo(tourRequest.getStartDate(), tourRequest.getEndDate());
            City city = notFoundCityEntityCheck(tourRequest.getCityId());
            Tour tour = Tour.builder()
                    .startData(tourRequest.getStartDate())
                    .endDate(tourRequest.getEndDate())
                    .city(city)
                    .build();
            tourRepository.save(tour);
        }catch (Exception e) {
            return new ResponseMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseMessage(HttpStatus.OK, "success");
    }


    /**
     * Tour modify process response message.
     *
     * @param tourRequest the tour request
     * @param tourId      the tour id
     * @return the response message
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage tourModifyProcess(TourRequest tourRequest, Long tourId) {
        localDateCompareTo(tourRequest.getStartDate(), tourRequest.getEndDate());
        Tour tour = notFoundTourEntityCheck(tourId);
        City city = notFoundCityEntityCheck(tourRequest.getCityId());
        tour.tourModify(city, tour.getStartData(), tour.getEndDate());
        return new ResponseMessage(HttpStatus.OK, "success");
    }

    /**
     * Tour delete process response message.
     *
     * @param tourId the tour id
     * @return the response message
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage tourDeleteProcess(Long tourId){
        Tour tour = notFoundTourEntityCheck(tourId);
        tourRepository.delete(tour);
        return new ResponseMessage(HttpStatus.OK, "success");
    }

    /**
     * Tour find one process response data message.
     *
     * @param tourId the tour id
     * @return the response data message
     */
    @Transactional(readOnly = true)
    public ResponseDataMessage<TourResponse> tourFindOneProcess(Long tourId) {
        Tour tour;
        try {
            tour = notFoundTourEntityCheck(tourId);
        }catch (Exception e) {
            return new ResponseDataMessage(HttpStatus.BAD_REQUEST, e.getMessage(), e.getMessage());
        }
        return new ResponseDataMessage(HttpStatus.OK, "success", tourResponseMapper.tourResponseDto(tour));
    }


    private void localDateCompareTo(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(endDate)) {
            return;
        }else {
            throw new DateTimeException("종료일자가 시작일자보다 빠릅니다.");
        }
    }

    private City notFoundCityEntityCheck(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 도시입니다."));
    }
    private Tour notFoundTourEntityCheck(Long tourId) {
        return tourRepository.findById(tourId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 여행정보입니다."));
    }

}
