package com.milk.vivekas.dao;

import com.milk.vivekas.enums.PaymentStatus;
import com.milk.vivekas.model.Payment;
import com.milk.vivekas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByCustomerAndStatus(User customer, PaymentStatus status);
}
