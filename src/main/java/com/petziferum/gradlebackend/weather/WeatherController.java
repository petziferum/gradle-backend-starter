package com.petziferum.gradlebackend.weather;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for fetching weather data.
 */
@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Get weather data for the default location.
     *
     * @return Weather data
     */
    @GetMapping("/")
    @Operation(
            summary = "Get weather data for the default location Trudering Riem",
            description = "Fetches current weather data from an external API for the default location",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Weather data retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Weather.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Weather> getWeather() {
        log.info("Received request to get weather data for default location");
        try {
            Weather weather = weatherService.getWeatherData();
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
            log.error("Error getting weather data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get weather data for a specific location.
     *
     * @param location The location to get weather data for
     * @return Weather data for the specified location
     */
    @GetMapping("/{location}")
    @Operation(
            summary = "Get weather data for a specific location",
            description = "Fetches current weather data from an external API for the specified location",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Weather data retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Weather.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<Weather> getWeatherForLocation(@PathVariable String location) {
        log.info("Received request to get weather data for location: {}", location);
        try {
            Weather weather = weatherService.getWeatherData(location);
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
            log.error("Error getting weather data for location {}: {}", location, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}