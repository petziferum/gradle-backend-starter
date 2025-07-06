package com.petziferum.gradlebackend.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    private Weather mockWeather;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        mockWeather = Weather.builder()
                .location("Berlin")
                .temperature(22.5)
                .description("Partly cloudy")
                .humidity(65.0)
                .windSpeed(5.2)
                .windDirection("NE")
                .pressure(1013.0)
                .lastUpdated("2023-06-15T10:30:00")
                .build();
    }

    @Test
    void getWeather_shouldReturnWeatherData() {
        // Arrange
        when(weatherService.getWeatherData()).thenReturn(mockWeather);

        // Act
        ResponseEntity<Weather> response = weatherController.getWeather();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Berlin", response.getBody().getLocation());
        assertEquals(22.5, response.getBody().getTemperature());
        
        verify(weatherService, times(1)).getWeatherData();
    }

    @Test
    void getWeatherForLocation_shouldReturnWeatherDataForSpecifiedLocation() {
        // Arrange
        String location = "Munich";
        Weather munichWeather = Weather.builder()
                .location(location)
                .temperature(20.0)
                .description("Sunny")
                .humidity(60.0)
                .windSpeed(4.0)
                .windDirection("SW")
                .pressure(1015.0)
                .lastUpdated("2023-06-15T10:30:00")
                .build();
        
        when(weatherService.getWeatherData(location)).thenReturn(munichWeather);

        // Act
        ResponseEntity<Weather> response = weatherController.getWeatherForLocation(location);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(location, response.getBody().getLocation());
        assertEquals(20.0, response.getBody().getTemperature());
        
        verify(weatherService, times(1)).getWeatherData(location);
    }

    @Test
    void getWeather_shouldHandleExceptions() {
        // Arrange
        when(weatherService.getWeatherData()).thenThrow(new RuntimeException("API error"));

        // Act
        ResponseEntity<Weather> response = weatherController.getWeather();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        
        verify(weatherService, times(1)).getWeatherData();
    }
}