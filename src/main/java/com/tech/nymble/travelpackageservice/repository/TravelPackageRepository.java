package com.tech.nymble.travelpackageservice.repository;

import com.tech.nymble.travelpackageservice.model.travel_package.TravelPackage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TravelPackageRepository extends MongoRepository<TravelPackage, String> {
}
