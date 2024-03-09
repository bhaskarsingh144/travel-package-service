package com.tech.nymble.travelpackageservice.repository;

import com.tech.nymble.travelpackageservice.model.activity.Activity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityQueryRepository {

    private final MongoTemplate mongoTemplate;

    public ActivityQueryRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Activity> getActivitiesByIds(List<String> activityIds) {
        Query query = new Query(Criteria.where("_id").in(activityIds));
        return mongoTemplate.find(query, Activity.class);
    }
}
