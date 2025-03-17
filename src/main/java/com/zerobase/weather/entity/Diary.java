package com.zerobase.weather.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {
    @Id
    @Builder.Default
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID diaryId = UUID.randomUUID();
    private String weather;
    private String icon;
    private double temperature;
    private String text;
    private LocalDate date;

    public void setText(String text) {
        this.text = text;
    }
}
