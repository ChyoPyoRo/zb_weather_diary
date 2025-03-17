package com.zerobase.weather.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CreateDiaryReqsDto {
    @NotNull
    private String date;
    @NotNull
    private String text;
}
