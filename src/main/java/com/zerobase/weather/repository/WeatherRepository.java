package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WeatherRepository extends JpaRepository<Weather, LocalDate> {
}
