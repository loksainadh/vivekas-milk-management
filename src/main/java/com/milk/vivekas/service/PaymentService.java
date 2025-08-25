package com.milk.vivekas.service;

import com.milk.vivekas.dao.PaymentRepository;
import com.milk.vivekas.dao.UserRepository;
import com.milk.vivekas.enums.PaymentStatus;
import com.milk.vivekas.model.Payment;
import com.milk.vivekas.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository payRepo;
    private final UserRepository userRepo;

    public PaymentService(PaymentRepository payRepo, UserRepository userRepo) {
        this.payRepo = payRepo;
        this.userRepo = userRepo;
    }

    public Payment create(Long userId, Payment p) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        p.setCustomer(u);
        if (p.getStatus() == null) p.setStatus(PaymentStatus.PENDING);
        return payRepo.save(p);
    }

    public List<Payment> byUserAndStatus(Long userId, PaymentStatus status) {
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        return payRepo.findByCustomerAndStatus(u, status);
    }
}
