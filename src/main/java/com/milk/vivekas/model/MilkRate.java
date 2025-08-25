package com.milk.vivekas.model;

import com.milk.vivekas.enums.MilkType;
import jakarta.persistence.*;

@Entity
@Table(name = "milk_rates")
public class MilkRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MilkType milkType;   // COW, BUFFALO, GOAT
    private double avgFatPercentage;
    private double maxFatPercentage; // Example: 4.0 for cow
private double maxRatePerLiter;
    public double getMaxRatePerLiter() {
	return maxRatePerLiter;
}


public void setMaxRatePerLiter(double maxRatePerLiter) {
	this.maxRatePerLiter = maxRatePerLiter;
}


	// Constructors
    public MilkRate() {}

    
    public double getMaxFatPercentage() {
		return maxFatPercentage;
	}


	public void setMaxFatPercentage(double maxFatPercentage) {
		this.maxFatPercentage = maxFatPercentage;
	}


	// Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public MilkType getMilkType() { return milkType; }
    public void setMilkType(MilkType milkType) { this.milkType = milkType; }
    public double getAvgFatPercentage() { return avgFatPercentage; }
    public void setAvgFatPercentage(double avgFatPercentage) { this.avgFatPercentage = avgFatPercentage; }
}
