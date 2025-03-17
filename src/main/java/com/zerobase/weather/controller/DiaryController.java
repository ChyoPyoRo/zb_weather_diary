package com.zerobase.weather.controller;

import com.zerobase.weather.dto.ResponseDto;
import com.zerobase.weather.enums.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiaryController {
    @PostMapping(value = "/create/diary")
    public ResponseEntity<ResponseDto> createDiary() {
        ResponseDto response = new ResponseDto(HttpStatus.CREATED, Description.SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping(value = "/read/diary")
    public ResponseEntity<ResponseDto> readDiary() {
        ResponseDto response = new ResponseDto(HttpStatus.CREATED, Description.SUCCESS);
        throw new RuntimeException();
//        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping(value="/read/diaries")
    public ResponseEntity<ResponseDto> readDiaries() {
        ResponseDto response = new ResponseDto(HttpStatus.CREATED, Description.SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping(value = "/update/diary")
    public ResponseEntity<ResponseDto> updateDiary(){
        ResponseDto response = new ResponseDto(HttpStatus.CREATED, Description.SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping(value="/delete/diary")
    public ResponseEntity<ResponseDto> deleteDiary(){
        ResponseDto response = new ResponseDto(HttpStatus.CREATED, Description.SUCCESS);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
