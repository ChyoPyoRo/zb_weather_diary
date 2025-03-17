package com.zerobase.weather.dto;

import com.zerobase.weather.entity.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReadDiariesRespDto {
    private List<ReadDiaryRespDto> diaries;

    public ReadDiariesRespDto(List<Diary> diaryList) {
        this.diaries = diaryList.stream()
                .map(ReadDiaryRespDto::from)
                .toList();
    }
}
