package com.zerobase.weather.controller;

import com.zerobase.weather.dto.CreateDiaryReqsDto;
import com.zerobase.weather.dto.ResponseDto;
import com.zerobase.weather.dto.UpdateDairyReqDto;
import com.zerobase.weather.enums.CustomeException;
import com.zerobase.weather.enums.Description;
import com.zerobase.weather.exception.CustomException;
import com.zerobase.weather.service.DiaryDetailService;
import com.zerobase.weather.utils.DateValidator;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiaryController {
    @Autowired
    private DiaryDetailService diaryDetailService;


    @PostMapping(value = "/create/diary")
    public ResponseEntity<ResponseDto> createDiary(@RequestBody CreateDiaryReqsDto requestDto) {
        if(!DateValidator.isValidDate(requestDto.getDate())) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.createDiary(requestDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping(value = "/read/diary")
    public ResponseEntity<ResponseDto> readDiary(@RequestParam String date) {
        if(date == null || !DateValidator.isValidDate(date)) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.findDiaryOnDate(date);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping(value="/read/diaries")
    public ResponseEntity<ResponseDto> readDiaries(@RequestParam String startDate, @RequestParam String endDate) {
        if(startDate == null || !DateValidator.isValidDate(startDate)) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        if(endDate == null || !DateValidator.isValidDate(endDate)) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.findAllDiaryOnDistance(startDate,endDate);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping(value = "/update/diary")
    public ResponseEntity<ResponseDto> updateDiary(@RequestBody UpdateDairyReqDto updateDairyReqDto){
        if(!DateValidator.isValidDate(updateDairyReqDto.getDate())) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.updateDiary(updateDairyReqDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping(value="/delete/diary")
    public ResponseEntity<ResponseDto> deleteDiary(@RequestParam String date){
        if(!DateValidator.isValidDate(date)) {throw new CustomException(CustomeException.INVALID_REQUEST);}
        ResponseDto response = diaryDetailService.deleteDiary(date);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
