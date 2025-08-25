package com.milk.vivekas.controller;

import com.milk.vivekas.model.Invoice;
import com.milk.vivekas.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService svc;

    public InvoiceController(InvoiceService svc) { this.svc = svc; }

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice,
                                          @RequestParam(required = false) Long userId,
                                          @RequestParam(required = false) Long paymentId) {
        return ResponseEntity.ok(svc.create(invoice, userId, paymentId));
    }

    @GetMapping
    public List<Invoice> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Invoice get(@PathVariable Long id) { return svc.get(id); }
}
