package com.milk.vivekas.dao;

import com.milk.vivekas.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    // no extra methods needed for basic upsert behavior
}
