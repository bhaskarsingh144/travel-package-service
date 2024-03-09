package com.tech.nymble.travelpackageservice.repository;

import com.tech.nymble.travelpackageservice.model.destination.Destination;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DestinationQueryRepository {

    private final MongoTemplate mongoTemplate;

    public DestinationQueryRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Destination> getDestinationsByIds(List<String> destinationIds) {
        Query query = new Query(Criteria.where("_id").in(destinationIds));
        return mongoTemplate.find(query, Destination.class);
    }
}
