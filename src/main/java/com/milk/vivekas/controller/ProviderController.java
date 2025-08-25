package com.milk.vivekas.controller;

import com.milk.vivekas.model.Provider;
import com.milk.vivekas.model.ProviderProfile;
import com.milk.vivekas.service.ProviderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {
    private final ProviderService svc;

    public ProviderController(ProviderService svc) { this.svc = svc; }

    @PostMapping
    public Provider create(@RequestBody Provider p) { return svc.create(p); }

    @GetMapping
    public List<Provider> all() { return svc.all(); }

    @GetMapping("/{id}")
    public Provider get(@PathVariable Long id) { return svc.get(id); }

    @PutMapping("/{id}")
    public Provider update(@PathVariable Long id, @RequestBody Provider p) { return svc.update(id, p); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { svc.delete(id); }

    @PostMapping("/{id}/profile")
    public ResponseEntity<ProviderProfile> saveProfile(@PathVariable Long id, @RequestBody ProviderProfile profile) {
        return ResponseEntity.ok(svc.saveProfile(id, profile));
    }
}
