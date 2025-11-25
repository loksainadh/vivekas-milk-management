package com.milk.vivekas.service;


import com.milk.vivekas.dao.WeatherRepository;
import com.milk.vivekas.dto.OpenWeatherResponse;
import com.milk.vivekas.dto.WeatherDto;

import com.milk.vivekas.exception.ThirdPartyBadResponseException;
import com.milk.vivekas.model.WeatherEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherRepository repository;
    private final String openWeatherUrl;
    private final String apiKey;
    private final String units;
    public WeatherService(RestTemplate restTemplate,
                          WeatherRepository repository,
                          @Value("${openweathermap.api.url}") String openWeatherUrl,
                          @Value("${openweathermap.api.key}") String apiKey,
                          @Value("${openweathermap.api.units:metric}") String units) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.openWeatherUrl = openWeatherUrl;
        this.apiKey = apiKey;
        this.units = units;
    }

    /**
     * Fetches current weather from OpenWeather, saves a record in DB, and returns a DTO.
     */
    public WeatherDto fetchAndSave(String city, String countryCode) {
        String q = city + (countryCode != null && !countryCode.isBlank() ? "," + countryCode : "");
        String url = String.format("%s?q=%s&APPID=%s&units=%s", openWeatherUrl, q, apiKey, units);

        try {
            OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);
            if (response == null) {
                throw new ThirdPartyBadResponseException("Bad response from OpenWeather API");
            }

            WeatherDto dto = toWeatherDto(response);
            saveDto(dto);
            return dto;
        } catch (RestClientException ex) {
            throw new ThirdPartyBadResponseException ("Failed to call OpenWeather API: " + ex.getMessage());
        }
    }

    private WeatherDto toWeatherDto(OpenWeatherResponse r) {
        String cityName = Optional.ofNullable(r.getName()).orElse("");
        Double temp = r.getMain() != null ? r.getMain().getTemp() : null;
        Integer humidity = r.getMain() != null ? r.getMain().getHumidity() : null;
        Double wind = r.getWind() != null ? r.getWind().getSpeed() : null;
        String description = (r.getWeather() != null && !r.getWeather().isEmpty())
                ? r.getWeather().get(0).getDescription()
                : null;

        return new WeatherDto(cityName, temp, humidity, wind, description);
    }

    private void saveDto(WeatherDto dto) {
        WeatherEntity entity = new WeatherEntity(
                dto.getCity(),
                dto.getTemp(),
                dto.getHumidity(),
                dto.getWindSpeed(),
                dto.getDescription(),
                Instant.now()
        );
        repository.save(entity);
    }

    /**
     * Return saved history for a city (most recent first).
     */
    public List<WeatherEntity> getHistory(String city, int limit) {
        List<WeatherEntity> list = repository.findByCityOrderByFetchedAtDesc(city);
        if (list == null) return List.of();
        if (limit <= 0) return list;
        return list.stream().limit(limit).toList();
    }
}
