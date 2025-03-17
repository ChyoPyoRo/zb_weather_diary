package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Diary;
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

    public Optional<Diary> findByDate(LocalDate date){
        return diaryRepository.findFirstByDate(date);
    }

    public Optional<List<Diary>> findAllByDateBetween(LocalDate startDate, LocalDate endDate){
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }
}
