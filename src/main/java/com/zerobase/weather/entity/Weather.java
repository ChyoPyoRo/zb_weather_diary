package com.zerobase.weather.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Weather {
    @Id
    private LocalDate date;
    @Column(nullable = false)
    private String weather;
    @Column(nullable = false)
    private double temperature;
    @Column(nullable = false)
    private String icon;
}
