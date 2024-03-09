package com.tech.nymble.travelpackageservice.repository;

import com.tech.nymble.travelpackageservice.model.passenger.Passenger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengerQueryRepository {

    private final MongoTemplate mongoTemplate;

    public PassengerQueryRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Passenger> getTravelPackagesByIds(List<String> passengerIds) {
        Query query = new Query(Criteria.where("_id").in(passengerIds));
        return mongoTemplate.find(query, Passenger.class);
    }
}
