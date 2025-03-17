package com.zerobase.weather.dto;

import com.zerobase.weather.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class ReadDiaryRespDto {
    private UUID id;
    private LocalDate date;
    private String weather;
    private double temperature;
    private String icon;
    private String text;

    public ReadDiaryRespDto(UUID id, LocalDate date, String weather, String icon, double temperature, String text) {
        this.id = id;
        this.date = date;
        this.weather = weather;
        this.icon = icon;
        this.temperature = temperature;
        this.text = text;
    }

    public static ReadDiaryRespDto from(Diary diary) {
        return new ReadDiaryRespDto(
                diary.getDiaryId(),
                diary.getDate(),
                diary.getWeather(),
                diary.getIcon(),
                diary.getTemperature(),
                diary.getText()
        );
    }
}
