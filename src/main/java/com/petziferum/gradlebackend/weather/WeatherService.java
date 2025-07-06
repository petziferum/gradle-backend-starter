package com.petziferum.gradlebackend.weather;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Service for fetching weather data from the OpenWeather API.
 */
@Service
@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${weather.api.url:https://api.openweathermap.org/data/2.5/weather}")
    private String weatherApiUrl;

    @Value("${weather.api.key:}")
    private String apiKey;

    @Value("${weather.default.location:München-Trudering}")
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
            // Build the URL with query parameters
            String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
            String url = UriComponentsBuilder.fromUriString(weatherApiUrl)
                    .queryParam("q", encodedLocation)
                    .queryParam("appid", apiKey)
                    .queryParam("units", "metric")  // Use metric units for temperature in Celsius
                    .build()
                    .toUriString();

            log.debug("Calling OpenWeather API with URL: {}", url.replace(apiKey, "API_KEY_HIDDEN"));

            // Make the API call
            OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);

            if (response == null) {
                throw new RuntimeException("Received null response from OpenWeather API");
            }

            // Convert the API response to our Weather model
            return response.toWeather(location);

        } catch (RestClientException e) {
            log.error("Error calling OpenWeather API: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch weather data from OpenWeather API", e);
        } catch (Exception e) {
            log.error("Error processing weather data: {}", e.getMessage());
            throw new RuntimeException("Failed to process weather data", e);
        }
    }
}
