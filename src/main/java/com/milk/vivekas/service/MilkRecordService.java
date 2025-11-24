package com.milk.vivekas.service;

import com.milk.vivekas.dao.MilkRateRepository;
import com.milk.vivekas.dao.MilkRecordRepository;
import com.milk.vivekas.dao.ProviderRepository;
import com.milk.vivekas.dao.UserRepository;
import com.milk.vivekas.exception.ResourceNotFoundException;
import com.milk.vivekas.model.MilkRate;
import com.milk.vivekas.model.MilkRecord;
import com.milk.vivekas.model.Provider;
import com.milk.vivekas.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MilkRecordService {
	@Autowired
    private MilkRecordRepository milkRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private  ProviderRepository providerRepo;
    @Autowired
   private MilkRateRepository milkRateRepository;
    
    
   
    public MilkRecord saverecord(Long userId, MilkRecord rec) {
        // 1) Validate input
        if (rec.getMilkType() == null) {
            throw new IllegalArgumentException("Milk type is required");
        }
        if (rec.getFatPercentage() <= 0) {
            throw new IllegalArgumentException("fatPercentage must be > 0");
        }
        if (rec.getQuantity() <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }

        // 2) Attach customer / provider / date
        User u = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        rec.setCustomer(u);

        if (rec.getProvider() == null && u.getProvider() != null) {
            rec.setProvider(u.getProvider());
        }
        if (rec.getDate() == null) {
            rec.setDate(LocalDate.now());
        }

        // 3) Get reference rate row for this milk type
        MilkRate rate = milkRateRepository.findTopByMilkTypeOrderByIdDesc(rec.getMilkType())
                .orElseThrow(() -> new IllegalArgumentException("Rate not defined for: " + rec.getMilkType()));

        // 4) Derive ₹ per 1% fat per liter from reference row
        //    e.g. if max=72 and avgFat=7.0 -> perFatRate ≈ 10.2857
        double perFatRate = rate.getMaxRatePerLiter() / rate.getAvgFatPercentage();
          System.out.println(perFatRate);
        // 5) Compute price per liter for this record based on ACTUAL fat%
        double calculated = rec.getFatPercentage() * perFatRate;
          System.out.println(calculated);
        // 6) Clamp to configured min..max band (optional, but common in dairies)
        //    If you don't want to force a minimum, remove the Math.max(...) and keep only Math.min(...)
        double pricePerLiter = Math.max(
                rate.getMaxRatePerLiter(),
                Math.max(calculated, rate.getMaxRatePerLiter())
        );

        // 7) Round to 2 decimals (money-safe)
        pricePerLiter = roundMoney(pricePerLiter);

        rec.setPricePerLiter(pricePerLiter);

        // 8) Total amount = quantity × pricePerLiter (rounded)
        double total = roundMoney(rec.getQuantity() * pricePerLiter);
        rec.setTotalAmount(total);

        // 9) Save
        return milkRepo.save(rec);
    }

    private double roundMoney(double value) {
        return new java.math.BigDecimal(value).setScale(2, java.math.RoundingMode.HALF_UP).doubleValue();
    }



    

    

    
    
    

	
	  public MilkRecord addForUser(Long userId, MilkRecord rec) { User u =
	  userRepo.findById(userId) .orElseThrow(() -> new
	  IllegalArgumentException("User not found: " + userId)); rec.setCustomer(u);
	  if (rec.getProvider() == null && u.getProvider() != null)
	  rec.setProvider(u.getProvider()); if (rec.getDate() == null)
	  rec.setDate(LocalDate.now());
	  
	  return milkRepo.save(rec); }
	 

    public List<MilkRecord> byUserAndMonth(Long userId, YearMonth ym) {
        User u = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        return milkRepo.findByCustomerAndDateBetween(u, ym.atDay(1), ym.atEndOfMonth());
    }

    public Map<String, Double> userMonthlyTotals(Long userId, YearMonth ym) {
        var list = byUserAndMonth(userId, ym);
        double liters = list.stream().mapToDouble(MilkRecord::getQuantity).sum();
        double amount = list.stream().mapToDouble(r -> r.getQuantity() * r.getTotalAmount()).sum();
        Map<String, Double> out = new HashMap<>();
        out.put("totalLiters", liters);
        out.put("totalAmount", amount);
        return out;
    }

    public List<MilkRecord> byProviderAndMonth(Long providerId, YearMonth ym) {
        Provider p = providerRepo.findById(providerId)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found: " + providerId));
        return milkRepo.findByProviderAndDateBetween(p, ym.atDay(1), ym.atEndOfMonth());
    }




	

	
}
