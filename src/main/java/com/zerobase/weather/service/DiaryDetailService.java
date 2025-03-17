package com.zerobase.weather.service;

import com.zerobase.weather.dto.CreateDiaryReqsDto;
import com.zerobase.weather.dto.ReadDiariesRespDto;
import com.zerobase.weather.dto.ReadDiaryRespDto;
import com.zerobase.weather.dto.ResponseDto;
import com.zerobase.weather.entity.Diary;
import com.zerobase.weather.entity.Weather;
import com.zerobase.weather.enums.CustomeException;
import com.zerobase.weather.enums.Description;
import com.zerobase.weather.exception.CustomException;
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
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public ResponseDto<?> findDiaryOnDate(String date){
        LocalDate targetDate = LocalDate.parse(date);
        Optional<Diary> diary = diaryDetailRepository.findByDate(targetDate);
        //일기 없을 때 null 처리
        if(diary.isEmpty()) throw new CustomException(CustomeException.DIARY_NOT_EXIST);
        ReadDiaryRespDto result = ReadDiaryRespDto.from(diary.get());
        return new ResponseDto<>(HttpStatus.OK, Description.SUCCESS, result);
    }

    @Transactional
    public ResponseDto<?> findAllDiaryOnDistance(String startDateReq, String endDateReq){
        LocalDate startDate = LocalDate.parse(startDateReq);
        LocalDate endDate = LocalDate.parse(endDateReq);
        Optional<List<Diary>> diaryList = diaryDetailRepository.findAllByDateBetween(startDate, endDate);
        if(diaryList.isEmpty()) throw new CustomException(CustomeException.DIARY_NOT_EXIST);
        ReadDiariesRespDto responseBody = new ReadDiariesRespDto(diaryList.get());
        return new ResponseDto<>(HttpStatus.OK, Description.SUCCESS, responseBody);
    }
}
