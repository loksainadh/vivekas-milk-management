package com.milk.vivekas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse {

    private Main main;
    private Wind wind;
    private List<Weather> weather;
    private String name;

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public Wind getWind() { return wind; }
    public void setWind(Wind wind) { this.wind = wind; }

    public List<Weather> getWeather() { return weather; }
    public void setWeather(List<Weather> weather) { this.weather = weather; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        private Double temp;
        private Integer humidity;

        public Double getTemp() { return temp; }
        public void setTemp(Double temp) { this.temp = temp; }

        public Integer getHumidity() { return humidity; }
        public void setHumidity(Integer humidity) { this.humidity = humidity; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        private Double speed;

        public Double getSpeed() { return speed; }
        public void setSpeed(Double speed) { this.speed = speed; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private String description;

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
