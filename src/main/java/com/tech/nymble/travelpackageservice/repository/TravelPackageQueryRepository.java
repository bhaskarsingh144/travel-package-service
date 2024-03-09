package com.tech.nymble.travelpackageservice.repository;

import com.tech.nymble.travelpackageservice.model.travel_package.TravelPackage;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TravelPackageQueryRepository {

    private final MongoTemplate mongoTemplate;

    public TravelPackageQueryRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<TravelPackage> getTravelPackagesByIds(List<String> travelPackageIds) {
        Query query = new Query(Criteria.where("_id").in(travelPackageIds));
        return mongoTemplate.find(query, TravelPackage.class);
    }
}
