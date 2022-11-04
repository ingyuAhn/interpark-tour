package com.assignment.interpark.api.tour.controller;

import com.assignment.interpark.api.tour.dto.request.TourRequest;
import com.assignment.interpark.api.tour.dto.response.TourResponse;
import com.assignment.interpark.api.tour.service.TourManagementService;
import com.assignment.interpark.common.message.ResponseDataMessage;
import com.assignment.interpark.common.message.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Tour", description = "여행 관리 API")
@RestController
@RequestMapping("/tour/management")
@RequiredArgsConstructor
public class TourManagementController {

    private final TourManagementService tourManagementService;

    @Operation(summary = "여행등록", description = "여행등록 할 수 있다.")
    @ApiResponse(responseCode = "200", description = "여행등록 성공")
    @PostMapping("")
    public ResponseMessage tourProduce(@RequestBody @Valid TourRequest tourRequest) {
        return tourManagementService.tourProduceProcess(tourRequest);
    }
    @Operation(summary = "여행수정", description = "여행수정 할 수 있다.")
    @ApiResponse(responseCode = "200", description = "여행수정 성공")
    @PutMapping("/{tourId}")
    public ResponseMessage tourModify(@PathVariable(value = "tourId")Long tourId,
                                      @RequestBody @Valid TourRequest tourRequest) {
        return tourManagementService.tourModifyProcess(tourRequest, tourId);
    }

    @Operation(summary = "여행삭제", description = "여행삭제 할 수 있다.")
    @ApiResponse(responseCode = "200", description = "여행삭제 성공")
    @DeleteMapping("/{tourId}")
    public ResponseMessage tourDelete(@PathVariable(value = "tourId")Long tourId) {
        return tourManagementService.tourDeleteProcess(tourId);
    }

    @Operation(summary = "여행단일조회", description = "여행 단일조회 할 수 있다.")
    @ApiResponse(responseCode = "200", description = "여행조회 성공",
            content = @Content(schema = @Schema(implementation = TourResponse.class)))
    @GetMapping("/{tourId}")
    public ResponseDataMessage<TourResponse> tourFind(@PathVariable(value = "tourId")Long tourId) {
        return tourManagementService.tourFindOneProcess(tourId);
    }


}
