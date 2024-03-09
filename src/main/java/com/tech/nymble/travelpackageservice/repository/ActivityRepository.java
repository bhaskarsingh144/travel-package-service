package com.tech.nymble.travelpackageservice.repository;

import com.tech.nymble.travelpackageservice.model.activity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
}
