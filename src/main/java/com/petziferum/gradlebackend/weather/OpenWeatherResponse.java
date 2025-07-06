package com.petziferum.gradlebackend.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Model class for mapping the response from the OpenWeather API.
 * Based on the structure of the OpenWeather API response.
 * See: https://openweathermap.org/current#current_JSON
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse {
    
    private Coord coord;
    private List<WeatherInfo> weather;
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Long dt;
    private Sys sys;
    private Integer timezone;
    private Long id;
    private String name;
    private Integer cod;
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coord {
        private Double lon;
        private Double lat;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherInfo {
        private Long id;
        private String main;
        private String description;
        private String icon;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        private Double temp;
        
        @JsonProperty("feels_like")
        private Double feelsLike;
        
        @JsonProperty("temp_min")
        private Double tempMin;
        
        @JsonProperty("temp_max")
        private Double tempMax;
        
        private Integer pressure;
        private Integer humidity;
        
        @JsonProperty("sea_level")
        private Integer seaLevel;
        
        @JsonProperty("grnd_level")
        private Integer groundLevel;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        private Double speed;
        private Integer deg;
        private Double gust;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Clouds {
        private Integer all;
    }
    
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sys {
        private Integer type;
        private Long id;
        private String country;
        private Long sunrise;
        private Long sunset;
    }
    
    /**
     * Converts this OpenWeatherResponse to a Weather object.
     * 
     * @param location The location name to use in the Weather object
     * @return A Weather object populated with data from this response
     */
    public Weather toWeather(String location) {
        String description = weather != null && !weather.isEmpty() ? 
                weather.get(0).getDescription() : "No description available";
        
        String windDirection = "";
        if (wind != null && wind.getDeg() != null) {
            int degrees = wind.getDeg();
            String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N"};
            windDirection = directions[(int)Math.round(degrees % 360 / 45.0)];
        }
        
        return Weather.builder()
                .location(location)
                .temperature(main != null ? main.getTemp() : null)
                .description(description)
                .humidity(main != null ? main.getHumidity().doubleValue() : null)
                .windSpeed(wind != null ? wind.getSpeed() : null)
                .windDirection(windDirection)
                .pressure(main != null ? main.getPressure().doubleValue() : null)
                .lastUpdated(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }
}