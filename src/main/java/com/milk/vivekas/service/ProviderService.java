package com.milk.vivekas.service;

import com.milk.vivekas.dao.ProviderProfileRepository;
import com.milk.vivekas.dao.ProviderRepository;
import com.milk.vivekas.model.Provider;
import com.milk.vivekas.model.ProviderProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    private final ProviderRepository providerRepo;
    private final ProviderProfileRepository profileRepo;

    public ProviderService(ProviderRepository providerRepo, ProviderProfileRepository profileRepo) {
        this.providerRepo = providerRepo;
        this.profileRepo = profileRepo;
    }

    public Provider create(Provider p) { return providerRepo.save(p); }
    public List<Provider> all() { return providerRepo.findAll(); }

    public Provider get(Long id) {
        return providerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found: " + id));
    }

    public Provider update(Long id, Provider update) {
        Provider p = get(id);
        p.setName(update.getName());
        p.setContactNumber(update.getContactNumber());
        return providerRepo.save(p);
    }

    public void delete(Long id) { providerRepo.deleteById(id); }

    public ProviderProfile saveProfile(Long providerId, ProviderProfile profile) {
        Provider p = get(providerId);
        profile.setProvider(p);
        return profileRepo.save(profile);
    }
}
