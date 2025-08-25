// src/main/java/com/milk/vivekas/dao/MilkRateRepository.java
package com.milk.vivekas.dao;

import com.milk.vivekas.enums.MilkType;
import com.milk.vivekas.model.MilkRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MilkRateRepository extends JpaRepository<MilkRate, Long> {

    // All rows for a given milk type (if you keep many versions/history)
    List<MilkRate> findByMilkType(MilkType milkType);

    // Most recent row for a type (if you insert multiple over time)
    Optional<MilkRate> findTopByMilkTypeOrderByIdDesc(MilkType milkType);

    // Check if a type already exists
    boolean existsByMilkType(MilkType milkType);
}
