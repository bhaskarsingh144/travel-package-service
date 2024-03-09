package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.activity.Activity;
import com.tech.nymble.travelpackageservice.repository.ActivityQueryRepository;
import com.tech.nymble.travelpackageservice.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityQueryRepository activityQueryRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository, ActivityQueryRepository activityQueryRepository) {
        this.activityRepository = activityRepository;
        this.activityQueryRepository = activityQueryRepository;
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity getActivityById(String id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivityById(String id) {
        activityRepository.deleteById(id);
    }

    @Override
    public List<Activity> getActivitiesByIds(List<String> activityIds) {
        return activityQueryRepository.getActivitiesByIds(activityIds);
    }
}
