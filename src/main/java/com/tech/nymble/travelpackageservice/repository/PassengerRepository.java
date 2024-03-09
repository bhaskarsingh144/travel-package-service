package com.tech.nymble.travelpackageservice.repository;

import com.tech.nymble.travelpackageservice.model.passenger.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends MongoRepository<Passenger, String> {
}
