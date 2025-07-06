package com.petziferum.gradlebackend.weather;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    private Weather mockWeather;

    @BeforeEach
    void setUp() {
        log.info("Setting up test data");
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
        log.debug("Created mock weather object: {}", mockWeather);
    }

    @Test
    void getWeather_shouldReturnWeatherData() {
        log.info("Testing getWeather method");
        // Arrange
        log.debug("Setting up mock for weatherService.getWeatherData()");
        when(weatherService.getWeatherData()).thenReturn(mockWeather);

        // Act
        log.debug("Calling weatherController.getWeather()");
        ResponseEntity<Weather> response = weatherController.getWeather();

        // Assert
        log.debug("Verifying response: {}", response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Berlin", response.getBody().getLocation());
        assertEquals(22.5, response.getBody().getTemperature());

        verify(weatherService, times(1)).getWeatherData();
        log.info("Test getWeather_shouldReturnWeatherData completed successfully");
    }

    @Test
    void getWeatherForLocation_shouldReturnWeatherDataForSpecifiedLocation() {
        log.info("Testing getWeatherForLocation method");
        // Arrange
        String location = "Munich";
        log.debug("Creating weather data for location: {}", location);
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

        log.debug("Setting up mock for weatherService.getWeatherData({})", location);
        when(weatherService.getWeatherData(location)).thenReturn(munichWeather);

        // Act
        log.debug("Calling weatherController.getWeatherForLocation({})", location);
        ResponseEntity<Weather> response = weatherController.getWeatherForLocation(location);

        // Assert
        log.debug("Verifying response for location {}: {}", location, response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(location, response.getBody().getLocation());
        assertEquals(20.0, response.getBody().getTemperature());

        verify(weatherService, times(1)).getWeatherData(location);
        log.info("Test getWeatherForLocation_shouldReturnWeatherDataForSpecifiedLocation completed successfully");
    }

    @Test
    void getWeather_shouldHandleExceptions() {
        log.info("Testing exception handling in getWeather method");
        // Arrange
        String errorMessage = "API error";
        log.debug("Setting up mock to throw exception: {}", errorMessage);
        when(weatherService.getWeatherData()).thenThrow(new RuntimeException(errorMessage));

        // Act
        log.debug("Calling weatherController.getWeather() which should handle the exception");
        ResponseEntity<Weather> response = weatherController.getWeather();

        // Assert
        log.debug("Verifying error response: {}", response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        log.warn("Received expected error response with status: {}", response.getStatusCode());

        verify(weatherService, times(1)).getWeatherData();
        log.info("Test getWeather_shouldHandleExceptions completed successfully");
    }
}
