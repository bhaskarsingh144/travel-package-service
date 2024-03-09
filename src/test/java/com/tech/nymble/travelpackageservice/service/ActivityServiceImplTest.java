package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.activity.Activity;
import com.tech.nymble.travelpackageservice.repository.ActivityQueryRepository;
import com.tech.nymble.travelpackageservice.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @InjectMocks
    private ActivityServiceImpl activityService;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ActivityQueryRepository activityQueryRepository;

    @Test
    void testGetAllActivities() {
        List<Activity> expectedActivities = Arrays.asList(
                new Activity("activity1", "Activity 1", "Description 1", 50.0, 100, 50, "destination1"),
                new Activity("activity2", "Activity 2", "Description 2", 75.0, 150, 75, "destination2")
        );

        when(activityRepository.findAll()).thenReturn(expectedActivities);

        List<Activity> actualActivities = activityService.getAllActivities();

        verify(activityRepository, times(1)).findAll();
        assertEquals(expectedActivities, actualActivities);
    }

    @Test
    void testGetActivityById() {
        String activityId = "activity1";
        Activity expectedActivity = new Activity(activityId, "Activity 1", "Description 1", 50.0, 100, 50, "destination1");

        when(activityRepository.findById(activityId)).thenReturn(Optional.of(expectedActivity));

        Activity actualActivity = activityService.getActivityById(activityId);

        verify(activityRepository, times(1)).findById(activityId);
        assertEquals(expectedActivity, actualActivity);
    }

    @Test
    void testSaveActivity() {
        Activity activityToSave = new Activity("activity1", "Activity 1", "Description 1", 50.0, 100, 50, "destination1");
        Activity expectedSavedActivity = new Activity("activity1", "Activity 1", "Description 1", 50.0, 100, 50, "destination1");

        when(activityRepository.save(activityToSave)).thenReturn(expectedSavedActivity);

        Activity actualSavedActivity = activityService.saveActivity(activityToSave);

        verify(activityRepository, times(1)).save(activityToSave);
        assertEquals(expectedSavedActivity, actualSavedActivity);
    }

    @Test
    void testDeleteActivityById() {
        String activityId = "activity1";

        activityService.deleteActivityById(activityId);

        verify(activityRepository, times(1)).deleteById(activityId);
    }

    @Test
    void testGetActivitiesByIds() {
        List<String> activityIds = Arrays.asList("activity1", "activity2");
        List<Activity> expectedActivities = Arrays.asList(
                new Activity("activity1", "Activity 1", "Description 1", 50.0, 100, 50, "destination1"),
                new Activity("activity2", "Activity 2", "Description 2", 75.0, 150, 75, "destination2")
        );

        when(activityQueryRepository.getActivitiesByIds(activityIds)).thenReturn(expectedActivities);

        List<Activity> actualActivities = activityService.getActivitiesByIds(activityIds);

        verify(activityQueryRepository, times(1)).getActivitiesByIds(activityIds);
        assertEquals(expectedActivities, actualActivities);
    }
}
