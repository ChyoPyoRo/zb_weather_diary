package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Diary;
import com.zerobase.weather.enums.CustomeException;
import com.zerobase.weather.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiaryDetailRepository {
    private final DiaryRepository diaryRepository;

    public void save(Diary diary) {
        diaryRepository.save(diary);
    }

    public Diary findByDate(LocalDate date){
        Optional<Diary> result = diaryRepository.findFirstByDate(date);
        if(result.isEmpty())throw new CustomException(CustomeException.DIARY_NOT_EXIST);
        return result.get();
    }

    public void updateDiary(LocalDate date, String text){
        Optional<Diary> result = diaryRepository.findFirstByDate(date);
        if(result.isEmpty())throw new CustomException(CustomeException.DIARY_NOT_EXIST);
        result.get().setText(text);
    }

    public Optional<List<Diary>> findAllByDateBetween(LocalDate startDate, LocalDate endDate){
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    public void deleteDiary(LocalDate date){
        diaryRepository.deleteAllByDate(date);
    }
}
