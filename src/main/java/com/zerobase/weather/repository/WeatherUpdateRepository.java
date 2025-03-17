package com.zerobase.weather.repository;

import com.zerobase.weather.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherUpdateRepository {
    @Autowired
    private WeatherRepository weatherRepository;

    public void save(Weather weather) {
        weatherRepository.save(weather);
    }
}
