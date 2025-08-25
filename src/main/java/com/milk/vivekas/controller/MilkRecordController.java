package com.milk.vivekas.controller;

import com.milk.vivekas.model.MilkRecord;
import com.milk.vivekas.service.MilkRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/milk-records")
public class MilkRecordController {
	@Autowired
    private MilkRecordService svc;

   // public MilkRecordController(MilkRecordService svc) { this.svc = svc; }

    @PostMapping("/user/{userId}")
    public ResponseEntity<MilkRecord> add(@PathVariable Long userId, @RequestBody MilkRecord rec) {
        return ResponseEntity.ok(svc.saverecord(userId, rec));
    }

    @GetMapping("/user/{userId}/month")
    public List<MilkRecord> byUserMonth(@PathVariable Long userId,
                                        @RequestParam int year,
                                        @RequestParam int month) {
        return svc.byUserAndMonth(userId, YearMonth.of(year, month));
    }

    @GetMapping("/user/{userId}/totals")
    public Map<String, Double> userMonthlyTotals(@PathVariable Long userId,
                                                 @RequestParam int year,
                                                 @RequestParam int month) {
        return svc.userMonthlyTotals(userId, YearMonth.of(year, month));
    }

    @GetMapping("/provider/{providerId}/month")
    public List<MilkRecord> byProviderMonth(@PathVariable Long providerId,
                                            @RequestParam int year,
                                            @RequestParam int month) {
        return svc.byProviderAndMonth(providerId, YearMonth.of(year, month));
    }
}
