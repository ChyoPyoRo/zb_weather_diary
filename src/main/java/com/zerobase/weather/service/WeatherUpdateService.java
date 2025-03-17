package com.zerobase.weather.service;

import com.zerobase.weather.entity.Weather;
import com.zerobase.weather.exception.WeatherApiException;
import com.zerobase.weather.repository.WeatherUpdateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class WeatherUpdateService {
    @Value("${API_KEY:}")
    private String apiKey;
    @Autowired
    private WeatherUpdateRepository weatherUpdateRepository;

    private final RestClient restClient = RestClient.create();


    @Scheduled(cron="0 0 1 * * *")
    public void updateWeather(){
        log.info("Update weather data");
        weatherUpdateRepository.save(getWeatherFromApi());
    }

    private Weather getWeatherFromApi(){
        if(apiKey == null || apiKey.isEmpty()){
            throw new WeatherApiException("API_KEY_NOT_FOUND");
        }
        Map response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("api.openweathermap.org")
                        .path("/data/2.5/weather")
                        .queryParam("lat", 37)
                        .queryParam("lon", 128)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build())
                .retrieve().body(Map.class);
        try{
            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
            String weather = (String) weatherList.get(0).get("main");
            String icon = (String) weatherList.get(0).get("icon");
            Map<String, Object> mainData = (Map<String, Object>) response.get("main");
            double temp = (double) mainData.get("temp");
            return Weather.builder()
                    .date(LocalDate.now())
                    .weather(weather)
                    .icon(icon)
                    .temperature(temp)
                    .build();
        }catch(NullPointerException ex){
            throw new WeatherApiException("API_CALL_FAILED");
        }
    }
}
