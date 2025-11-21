package com.milk.vivekas.service;

import com.milk.vivekas.dao.ProviderProfileRepository;
import com.milk.vivekas.dao.ProviderRepository;
import com.milk.vivekas.dto.ProviderDto;
import com.milk.vivekas.model.Provider;
import com.milk.vivekas.model.ProviderProfile;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Transactional
    public List<Provider> upsertProviders(List<ProviderDto> providerDtos) {
        if (providerDtos == null || providerDtos.isEmpty()) {
            return Collections.emptyList();
        }

        // Collect ids that are non-null
        List<Long> ids = providerDtos.stream()
                .map(ProviderDto::getId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // Fetch existing providers for those ids
        Map<Long, Provider> existingMap = Collections.emptyMap();
        if (!ids.isEmpty()) {
            existingMap = providerRepo.findAllById(ids)
                    .stream()
                    .collect(Collectors.toMap(Provider::getId, p -> p));
        }

        List<Provider> toSave = new ArrayList<>();

        for (ProviderDto dto : providerDtos) {
            if (dto.getId() != null && existingMap.containsKey(dto.getId())) {
                // Update existing
                Provider existing = existingMap.get(dto.getId());
                // update fields (only those you want - here all)
                existing.setName(dto.getName());
                existing.setContactNumber(dto.getContactNumber());
                toSave.add(existing);
            } else {
                // New entity (either id null or id not found)
                Provider newProvider = new Provider();
                // Do not set ID (let JPA generate it). If you want to accept client-supplied ID for new records,
                // you'll need to ensure the generation strategy aligns and uniqueness is checked.
                newProvider.setName(dto.getName());
                newProvider.setContactNumber(dto.getContactNumber());
                toSave.add(newProvider);
            }
        }

        // saveAll will perform batch save/update inside transaction
        return providerRepo.saveAll(toSave);
    }











}
