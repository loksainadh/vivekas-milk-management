package com.milk.vivekas.model;

import com.milk.vivekas.enums.MilkType;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "milk_records")
public class MilkRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double fatPercentage;
    private double quantity; // liters
    
	private double totalAmount ;
    private double pricePerLiter;
    public double getPricePerLiter() {
		return pricePerLiter;
	}



	public void setPricePerLiter(double pricePerLiter) {
		this.pricePerLiter = pricePerLiter;
	}
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MilkType milkType;  // NEW

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User customer;

    public MilkRecord() {}

   

    public double getFatPercentage() {
		return fatPercentage;
	}



	public void setFatPercentage(double fatPercentage) {
		this.fatPercentage = fatPercentage;
	}
	 public double getTotalAmount() {
			return totalAmount;
		}



		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}


	// Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

   

    public MilkType getMilkType() { return milkType; }
    public void setMilkType(MilkType milkType) { this.milkType = milkType; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Provider getProvider() { return provider; }
    public void setProvider(Provider provider) { this.provider = provider; }

    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }
}
