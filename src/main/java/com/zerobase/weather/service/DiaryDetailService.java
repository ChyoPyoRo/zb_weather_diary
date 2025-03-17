package com.zerobase.weather.service;

import com.zerobase.weather.dto.CreateDiaryReqsDto;
import com.zerobase.weather.dto.ResponseDto;
import com.zerobase.weather.entity.Diary;
import com.zerobase.weather.entity.Weather;
import com.zerobase.weather.enums.Description;
import com.zerobase.weather.repository.DiaryDetailRepository;
import com.zerobase.weather.repository.WeatherRepository;
import com.zerobase.weather.repository.WeatherUpdateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryDetailService {
    private final DiaryDetailRepository diaryDetailRepository;
    private final WeatherUpdateRepository weatherUpdateRepository;

    @Transactional
    public ResponseDto<?> createDiary(CreateDiaryReqsDto requestDto){
        LocalDate date = LocalDate.parse(requestDto.getDate());
        Weather weatherDetail = weatherUpdateRepository.getWeather(date);

        Diary diary = Diary.builder()
                .weather(weatherDetail.getWeather())
                .date(date)
                .icon(weatherDetail.getIcon())
                .temperature(weatherDetail.getTemperature())
                .text(requestDto.getText())
                .build();
        diaryDetailRepository.save(diary);
        return ResponseDto.builder().description(Description.SUCCESS).status(HttpStatus.CREATED).build();
    }
}
