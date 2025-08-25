package com.milk.vivekas.model;

import com.milk.vivekas.enums.PaymentMethod;
import com.milk.vivekas.enums.PaymentStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method;  // NEW

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;  // NEW

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Invoice invoice;

    
    public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User customer;

    public Payment() {}

    public Payment(Long id, double amount, PaymentMethod method, PaymentStatus status, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.date = date;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }
}
