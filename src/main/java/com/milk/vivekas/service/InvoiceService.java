package com.milk.vivekas.service;

import com.milk.vivekas.dao.InvoiceRepository;
import com.milk.vivekas.dao.PaymentRepository;
import com.milk.vivekas.dao.UserRepository;
import com.milk.vivekas.model.Invoice;
import com.milk.vivekas.model.Payment;
import com.milk.vivekas.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invRepo;
    private final PaymentRepository payRepo;
    private final UserRepository userRepo;

    public InvoiceService(InvoiceRepository invRepo, PaymentRepository payRepo, UserRepository userRepo) {
        this.invRepo = invRepo; this.payRepo = payRepo; this.userRepo = userRepo;
    }

    public Invoice create(Invoice invoice, Long userId, Long paymentId) {
        if (invoice.getBillingDate() == null) {
            invoice.setBillingDate(LocalDate.now());
        }
        if (userId != null) {
            User u = userRepo.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
            invoice.setCustomer(u);
        }
        if (paymentId != null) {
            Payment p = payRepo.findById(paymentId)
                    .orElseThrow(() -> new IllegalArgumentException("Payment not found: " + paymentId));
            invoice.setPayment(p);
        }
        return invRepo.save(invoice);
    }

    public List<Invoice> all() { return invRepo.findAll(); }

    public Invoice get(Long id) {
        return invRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invoice not found: " + id));
    }
}
