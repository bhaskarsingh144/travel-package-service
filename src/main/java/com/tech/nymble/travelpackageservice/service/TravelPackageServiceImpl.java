package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.travel_package.TravelPackage;
import com.tech.nymble.travelpackageservice.repository.TravelPackageQueryRepository;
import com.tech.nymble.travelpackageservice.repository.TravelPackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {

    private final TravelPackageRepository travelPackageRepository;
    private final TravelPackageQueryRepository travelPackageQueryRepository;

    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository,
                                    TravelPackageQueryRepository travelPackageQueryRepository) {
        this.travelPackageRepository = travelPackageRepository;
        this.travelPackageQueryRepository = travelPackageQueryRepository;
    }

    @Override
    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageRepository.findAll();
    }

    @Override
    public TravelPackage getTravelPackageById(String id) {
        return travelPackageRepository.findById(id).orElse(null);
    }

    @Override
    public TravelPackage saveTravelPackage(TravelPackage travelPackage) {
        return travelPackageRepository.save(travelPackage);
    }

    @Override
    public void deleteTravelPackageById(String id) {
        travelPackageRepository.deleteById(id);
    }


    @Override
    public List<TravelPackage> getTravelPackagesByIds(List<String> travelPackageIds) {
        return travelPackageQueryRepository.getTravelPackagesByIds(travelPackageIds);
    }
}
