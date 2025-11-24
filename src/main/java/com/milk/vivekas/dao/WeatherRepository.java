package com.milk.vivekas.dao;


import com.milk.vivekas.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    List<WeatherEntity> findByCityOrderByFetchedAtDesc(String city);
}
