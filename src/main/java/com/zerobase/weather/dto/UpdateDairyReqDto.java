package com.zerobase.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class UpdateDairyReqDto {
    @NonNull
    private String date;

    @NonNull
    private String text;
}
