package com.assignment.interpark.api.city.service;

import com.assignment.interpark.api.city.dto.request.CityRequest;
import com.assignment.interpark.api.city.mapper.CityResponseMapper;
import com.assignment.interpark.api.city.repository.CityRepository;
import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.api.tour.repository.TourRepository;
import com.assignment.interpark.common.message.ResponseDataMessage;
import com.assignment.interpark.common.message.ResponseMessage;
import com.assignment.interpark.domain.tour.City;
import com.assignment.interpark.domain.tour.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type City management service.
 */
@Service
public class CityManagementService {

    private final CityRepository cityRepository;
    private final CityResponseMapper cityResponseMapper;
    private final TourRepository tourRepository;

    /**
     * Instantiates a new City management service.
     *
     * @param cityRepository     the city repository
     * @param cityResponseMapper the city response mapper
     */
    public CityManagementService(CityRepository cityRepository,
                                 CityResponseMapper cityResponseMapper,
                                 TourRepository tourRepository) {
        this.cityRepository = cityRepository;
        this.cityResponseMapper = cityResponseMapper;
        this.tourRepository = tourRepository;
    }

    /**
     * City produce process response message.
     *
     * @param cityRequest the city request
     * @return the response message
     */
    @Transactional
    public ResponseMessage cityProduceProcess(CityRequest cityRequest) {

        try{
            duplicateCityNameCheck(cityRequest.getCityName());

            City city = City.builder()
                    .cityName(cityRequest.getCityName())
                    .population(cityRequest.getPopulation())
                    .build();
            cityRepository.save(city);
        }catch (Exception e){
            return new ResponseMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseMessage(HttpStatus.OK, "success");
    }

    /**
     * City modify process response message.
     *
     * @param cityId      the city id
     * @param cityRequest the city request
     * @return the response message
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage cityModifyProcess(Long cityId, CityRequest cityRequest){
        City city = notFoundCityEntityCheck(cityId);
        city.cityModify(cityRequest.getCityName(), cityRequest.getPopulation());
        return new ResponseMessage(HttpStatus.OK, "success");
    }

    /**
     * City delete process response message.
     *
     * @param cityId the city id
     * @return the response message
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage cityDeleteProcess(Long cityId) {
        City city = notFoundCityEntityCheck(cityId);
        cityRepository.delete(city);
        return new ResponseMessage(HttpStatus.OK, "success");
    }

    /**
     * City find one process response data message.
     *
     * @param cityId the city id
     * @return the response data message
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDataMessage cityFindOneProcess(Long cityId) {
        City city = notFoundCityEntityCheck(cityId);
        //마지막 조회한 경우 업데이트함
        city.lastClickDateModify();;
        List<Tour> tourList = tourRepository.findByCity(city);
        return new ResponseDataMessage(HttpStatus.OK, "success", cityResponseMapper.cityOrTourListResponseDto(city, tourList));
    }

    /**
     * City find list process response data message.
     *
     * @return the response data message
     */
    @Transactional(readOnly = true)
    public ResponseDataMessage cityFindListProcess() {

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createDate");
        List<TourResponse> tourList = tourRepository.findByTourQuery();

        //여행예정 or 여행중 도시가 있는경우 Tour 에서 조회
        if (tourList.isEmpty()) {
            Page<City> cityList = cityRepository.findAll(pageable);
            return new ResponseDataMessage(HttpStatus.OK, "success",
                    cityResponseMapper.cityResponseListDto(cityList.toList()));
        }
        //위 상황 아닐경우 createDate 기준 Desc 하여 10개 호출
        return new ResponseDataMessage(HttpStatus.OK, "success",
                tourList);
    }

    private void duplicateCityNameCheck(String cityName) {
        cityRepository.findByCityName(cityName).ifPresent(city -> {
            throw new EntityExistsException("기존에 존재하는 도시 이름입니다.");
        });
    }

    private City notFoundCityEntityCheck(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 도시입니다."));
    }
}
