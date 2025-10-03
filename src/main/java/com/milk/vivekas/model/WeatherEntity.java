package com.milk.vivekas.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "weather_history")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private Double temp;
    private Integer humidity;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "fetched_at")
    private Instant fetchedAt;

    public WeatherEntity() {}

    public WeatherEntity(String city, Double temp, Integer humidity, Double windSpeed, String description, Instant fetchedAt) {
        this.city = city;
        this.temp = temp;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.description = description;
        this.fetchedAt = fetchedAt;
    }

    // getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public Instant getFetchedAt() { return fetchedAt; }
    public void setFetchedAt(Instant fetchedAt) { this.fetchedAt = fetchedAt; }
}
