package com.tech.nymble.travelpackageservice.repository;

import com.tech.nymble.travelpackageservice.model.destination.Destination;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends MongoRepository<Destination, String> {
}
