package com.zerobase.weather.controller;

import com.zerobase.weather.dto.CreateDiaryReqsDto;
import com.zerobase.weather.dto.ResponseDto;
import com.zerobase.weather.dto.UpdateDairyReqDto;
import com.zerobase.weather.enums.CustomeException;
import com.zerobase.weather.enums.Description;
import com.zerobase.weather.exception.CustomException;
import com.zerobase.weather.service.DiaryDetailService;
import com.zerobase.weather.utils.DateValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Diary Api", description = "일기 관련 APi")
public class DiaryController {
    @Autowired
    private DiaryDetailService diaryDetailService;


    @PostMapping(value = "/create/diary")
    @Operation(summary = "일기 생성", description = "캐싱되어 있는 날씨 정보를 기반으로 일기 생성")
    public ResponseEntity<ResponseDto> createDiary(@RequestBody CreateDiaryReqsDto requestDto) {
        if(!DateValidator.isValidDate(requestDto.getDate())) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.createDiary(requestDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping(value = "/read/diary")
    @Operation(summary = "일기 불러오기", description = "하나의 일기만 불러옴")
    public ResponseEntity<ResponseDto> readDiary(@RequestParam String date) {
        if(date == null || !DateValidator.isValidDate(date)) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.findDiaryOnDate(date);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping(value="/read/diaries")
    @Operation(summary="기간 내 일기 불러오기", description="기간 내의 일기를 전부 불러옴")
    public ResponseEntity<ResponseDto> readDiaries(@RequestParam String startDate, @RequestParam String endDate) {
        if(startDate == null || !DateValidator.isValidDate(startDate)) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        if(endDate == null || !DateValidator.isValidDate(endDate)) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.findAllDiaryOnDistance(startDate,endDate);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping(value = "/update/diary")
    @Operation(summary="일기 수정하기", description = "해당 날짜의 일기들 중 첫번째 일기 수정")
    public ResponseEntity<ResponseDto> updateDiary(@RequestBody UpdateDairyReqDto updateDairyReqDto){
        if(!DateValidator.isValidDate(updateDairyReqDto.getDate())) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.updateDiary(updateDairyReqDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping(value="/delete/diary")
    @Operation(summary="일기 삭제하기", description = "해당 날짜의 모든 일기 삭제")
    public ResponseEntity<ResponseDto> deleteDiary(@RequestParam String date){
        if(!DateValidator.isValidDate(date)) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.deleteDiary(date);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
