package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.activity.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> getAllActivities();

    Activity getActivityById(String id);

    Activity saveActivity(Activity activity);

    void deleteActivityById(String id);

    List<Activity> getActivitiesByIds(List<String> activityIds);
}
