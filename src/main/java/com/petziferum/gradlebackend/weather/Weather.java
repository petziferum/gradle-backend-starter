package com.petziferum.gradlebackend.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class representing weather data for a location.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private String location;
    private Double temperature;
    private String description;
    private Double humidity;
    private Double windSpeed;
    private String windDirection;
    private Double pressure;
    private String lastUpdated;
}