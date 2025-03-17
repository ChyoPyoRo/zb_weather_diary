package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class WeatherUpdateRepository {
    private final WeatherRepository weatherRepository;

    public void save(Weather weather) {
        weatherRepository.save(weather);
    }

    public Weather getWeather(LocalDate date) {
        return weatherRepository.findByDate(date);
    }
}
