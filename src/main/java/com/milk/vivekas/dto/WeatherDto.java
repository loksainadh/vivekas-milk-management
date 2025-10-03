package com.milk.vivekas.dto;

public class WeatherDto {
    private String city;
    private Double temp;
    private Integer humidity;
    private Double windSpeed;
    private String description;

    public WeatherDto() {}

    public WeatherDto(String city, Double temp, Integer humidity, Double windSpeed, String description) {
        this.city = city;
        this.temp = temp;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Double getTemp() { return temp; }
    public void setTemp(Double temp) { this.temp = temp; }

    public Integer getHumidity() { return humidity; }
    public void setHumidity(Integer humidity) { this.humidity = humidity; }

    public Double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(Double windSpeed) { this.windSpeed = windSpeed; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
