package com.milk.vivekas.controller;


import com.milk.vivekas.dto.WeatherDto;
import com.milk.vivekas.model.WeatherEntity;
import com.milk.vivekas.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    /**
     * Fetch current weather from OpenWeather and save it.
     * Example: GET /api/weather/fetch?city=London&country=uk
     */
    @GetMapping("/fetch")
    public ResponseEntity<WeatherDto> fetchAndSave(@RequestParam String city,
                                                   @RequestParam(required = false) String country) {
        WeatherDto dto = service.fetchAndSave(city, country);
        System.out.println("loksainadh");
        return ResponseEntity.ok(dto);

    }

    /**
     * Return saved history for a city.
     * Example: GET /api/weather/history?city=London&limit=10
     */
    @GetMapping("/history")
    public ResponseEntity<List<WeatherEntity>> getHistory(@RequestParam String city,
                                                          @RequestParam(defaultValue = "50") int limit) {
        List<WeatherEntity> history = service.getHistory(city, limit);
        return ResponseEntity.ok(history);

    }
}
