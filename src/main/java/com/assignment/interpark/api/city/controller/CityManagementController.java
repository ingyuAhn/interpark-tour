package com.assignment.interpark.api.city.controller;

import com.assignment.interpark.api.city.dto.request.CityRequest;
import com.assignment.interpark.api.city.dto.response.CityResponse;
import com.assignment.interpark.api.city.service.CityManagementService;
import com.assignment.interpark.api.tour.dto.request.TourRequest;
import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.common.message.ResponseDataMessage;
import com.assignment.interpark.common.message.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "City", description = "도시 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/city/management")
public class CityManagementController {

    private final CityManagementService cityManagementService;

    @Operation(summary = "도시등록", description = "도시등록 할 수 있다.")
    @ApiResponse(responseCode = "200", description = "도시등록 성공")
    @PostMapping("")
    public ResponseMessage cityProduce(@RequestBody @Valid CityRequest cityRequest) {
        return cityManagementService.cityProduceProcess(cityRequest);
    }
    @Operation(summary = "도시수정", description = "도시수정 할 수 있다.")
    @ApiResponse(responseCode = "200", description = "도시수정 성공")
    @PutMapping("/{cityId}")
    public ResponseMessage tourModify(@PathVariable(value = "cityId")Long cityId,
                                      @RequestBody @Valid CityRequest cityRequest) {
        return cityManagementService.cityModifyProcess(cityId, cityRequest);
    }

    @Operation(summary = "도시삭제", description = "도시삭제 할 수 있다.")
    @ApiResponse(responseCode = "200", description = "도시삭제 성공")
    @DeleteMapping("/{cityId}")
    public ResponseMessage tourDelete(@PathVariable(value = "cityId")Long cityId) {
        return cityManagementService.cityDeleteProcess(cityId);
    }

    @Operation(summary = "도시단일조회", description = "도시 단일조회 할 수 있다.")
    @ApiResponse(responseCode = "200", description = "도시조회 성공",
            content = @Content(schema = @Schema(implementation = CityResponse.class)))
    @GetMapping("/{cityId}")
    public ResponseDataMessage<CityResponse> tourFind(@PathVariable(value = "cityId")Long cityId) {
        return cityManagementService.cityFindOneProcess(cityId);
    }

    @Operation(summary = "도시조회", description = "도시 조회 할 수 있다.")
    @ApiResponse(responseCode = "200", description = "도시조회 여행일정X",
            content = @Content(schema = @Schema(implementation = CityResponse.class)))
    @ApiResponse(responseCode = "200", description = "도시조회 여행일정O",
            content = @Content(schema = @Schema(implementation = TourResponse.class)))
    @GetMapping("")
    public ResponseDataMessage tourFindList() {
        return cityManagementService.cityFindListProcess();
    }
}
