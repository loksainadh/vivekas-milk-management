package com.milk.vivekas.dao;

import com.milk.vivekas.model.MilkRecord;
import com.milk.vivekas.model.Provider;
import com.milk.vivekas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MilkRecordRepository extends JpaRepository<MilkRecord, Long> {
    List<MilkRecord> findByCustomerAndDateBetween(User customer, LocalDate from, LocalDate to);
    List<MilkRecord> findByProviderAndDateBetween(Provider provider, LocalDate from, LocalDate to);
}
