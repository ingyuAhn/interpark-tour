package com.assignment.interpark.api.city.service;

import com.assignment.interpark.api.city.dto.request.CityRequest;
import com.assignment.interpark.api.city.mapper.CityResponseMapper;
import com.assignment.interpark.api.city.repository.CityRepository;
import com.assignment.interpark.common.message.ResponseDataMessage;
import com.assignment.interpark.common.message.ResponseMessage;
import com.assignment.interpark.domain.tour.City;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

/**
 * The type City management service.
 */
@Service
public class CityManagementService {

    private final CityRepository cityRepository;
    private final CityResponseMapper cityResponseMapper;

    /**
     * Instantiates a new City management service.
     *
     * @param cityRepository     the city repository
     * @param cityResponseMapper the city response mapper
     */
    public CityManagementService(CityRepository cityRepository,
                                 CityResponseMapper cityResponseMapper) {
        this.cityRepository = cityRepository;
        this.cityResponseMapper = cityResponseMapper;
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
        try {
            City city = notFoundCityEntityCheck(cityId);
            city.cityModify(cityRequest.getCityName(), cityRequest.getPopulation());
        }catch (Exception e) {
            return new ResponseMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        }
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
        try {
            City city = notFoundCityEntityCheck(cityId);
            cityRepository.delete(city);
        }catch (Exception e) {
            return new ResponseMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ResponseMessage(HttpStatus.OK, "success");
    }

    /**
     * City find one process response data message.
     *
     * @param cityId the city id
     * @return the response data message
     */
    @Transactional(readOnly = true)
    public ResponseDataMessage cityFindOneProcess(Long cityId) {
        City city;
        try {
            city = notFoundCityEntityCheck(cityId);
        }catch (Exception e) {
            return new ResponseDataMessage(HttpStatus.BAD_REQUEST, e.getMessage(), "fail");
        }
        return new ResponseDataMessage(HttpStatus.OK, "success", cityResponseMapper.cityResponseDto(city));
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
