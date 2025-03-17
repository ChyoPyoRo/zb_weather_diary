package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Diary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DiaryDetailRepository {
    private final DiaryRepository diaryRepository;

    public void save(Diary diary) {
        diaryRepository.save(diary);
    }

}
