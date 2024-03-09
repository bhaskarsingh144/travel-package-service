package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.travel_package.TravelPackage;

import java.util.List;

public interface TravelPackageService {
    List<TravelPackage> getAllTravelPackages();

    TravelPackage getTravelPackageById(String id);

    TravelPackage saveTravelPackage(TravelPackage travelPackage);

    void deleteTravelPackageById(String id);

    List<TravelPackage> getTravelPackagesByIds(List<String> travelPackageIds);
}
