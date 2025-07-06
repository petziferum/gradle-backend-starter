package com.petziferum.gradlebackend.weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service for fetching weather data from an external API.
 */
@Service
@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate;
    
    @Value("${weather.api.url:https://api.openweathermap.org/data/2.5/weather}")
    private String weatherApiUrl;
    
    @Value("${weather.api.key:}")
    private String apiKey;
    
    @Value("${weather.default.location:Berlin}")
    private String defaultLocation;

    public WeatherService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Fetches weather data for the default location.
     * 
     * @return Weather data for the default location
     */
    public Weather getWeatherData() {
        return getWeatherData(defaultLocation);
    }

    /**
     * Fetches weather data for a specific location.
     * 
     * @param location The location to fetch weather data for
     * @return Weather data for the specified location
     */
    public Weather getWeatherData(String location) {
        log.info("Fetching weather data for location: {}", location);
        
        try {
            // In a real implementation, this would call the external API
            // String url = weatherApiUrl + "?q=" + location + "&appid=" + apiKey + "&units=metric";
            // WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);
            
            // For now, we'll return mock data
            return Weather.builder()
                    .location(location)
                    .temperature(22.5)
                    .description("Partly cloudy")
                    .humidity(65.0)
                    .windSpeed(5.2)
                    .windDirection("NE")
                    .pressure(1013.0)
                    .lastUpdated(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .build();
        } catch (Exception e) {
            log.error("Error fetching weather data: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch weather data", e);
        }
    }
}