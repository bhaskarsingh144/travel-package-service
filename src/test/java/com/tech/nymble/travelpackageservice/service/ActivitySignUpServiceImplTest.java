package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.activity.Activity;
import com.tech.nymble.travelpackageservice.model.passenger.AccountLevelType;
import com.tech.nymble.travelpackageservice.model.passenger.Passenger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.assertions.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivitySignUpServiceImplTest {

    @InjectMocks
    private ActivitySignUpServiceImpl activitySignUpService;
    @Mock
    private ActivityService activityService;
    @Mock
    private PassengerService passengerService;
    @Captor
    private ArgumentCaptor<Passenger> passengerCaptor;
    @Captor
    private ArgumentCaptor<Activity> activityCaptor;

    @Test
    void testSignUpStandardPassenger_Success() {
        String passengerId = "passenger1";
        String activityId = "activity1";
        Passenger passenger = new Passenger(passengerId, "Passenger 1", "Rakesh", AccountLevelType.STANDARD, 100.0, new ArrayList<>());
        Activity activity = new Activity(activityId, "Activity 1", "Description 1", 50.0, 100, 50, "destination1");

        when(passengerService.getPassengerById(passengerId)).thenReturn(passenger);
        when(activityService.getActivityById(activityId)).thenReturn(activity);
        when(activityService.saveActivity(any(Activity.class))).thenReturn(activity);
        when(passengerService.savePassenger(any(Passenger.class))).thenReturn(passenger);

        boolean signUpSuccess = activitySignUpService.signUp(passengerId, activityId);

        verify(passengerService, times(1)).savePassenger(passengerCaptor.capture());
        verify(activityService, times(1)).saveActivity(activityCaptor.capture());

        Passenger updatedPassenger = passengerCaptor.getValue();
        Activity updatedActivity = activityCaptor.getValue();

        assertTrue(signUpSuccess);
        assertEquals(50.0, updatedPassenger.getBalance());
        assertEquals(99, updatedActivity.getCapacity());
    }

    @Test
    void testSignUpGoldPassenger_Success() {
        String passengerId = "passenger2";
        String activityId = "activity2";
        Passenger passenger = new Passenger(passengerId, "Passenger 2", "Rakesh", AccountLevelType.GOLD, 0.0, new ArrayList<>());
        Activity activity = new Activity(activityId, "Activity 2", "Description 2", 75.0, 150, 75, "destination2");

        when(passengerService.getPassengerById(passengerId)).thenReturn(passenger);
        when(activityService.getActivityById(activityId)).thenReturn(activity);
        when(activityService.saveActivity(any(Activity.class))).thenReturn(activity);
        when(passengerService.savePassenger(any(Passenger.class))).thenReturn(passenger);

        boolean signUpSuccess = activitySignUpService.signUp(passengerId, activityId);

        verify(passengerService, times(1)).savePassenger(passengerCaptor.capture());
        verify(activityService, times(1)).saveActivity(activityCaptor.capture());

        Passenger updatedPassenger = passengerCaptor.getValue();
        Activity updatedActivity = activityCaptor.getValue();

        assertTrue(signUpSuccess);
        assertEquals(0.0, updatedPassenger.getBalance());
        assertEquals(74, updatedActivity.getCapacity());
    }

    @Test
    void testSignUpPremiumPassenger_Success() {
        String passengerId = "passenger3";
        String activityId = "activity3";
        Passenger passenger = new Passenger(passengerId, "Passenger 3", "Rakesh", AccountLevelType.PREMIUM, 0.0, new ArrayList<>());
        Activity activity = new Activity(activityId, "Activity 3", "Description 3", 100.0, 200, 199, "destination3");

        when(passengerService.getPassengerById(passengerId)).thenReturn(passenger);
        when(activityService.getActivityById(activityId)).thenReturn(activity);
        when(activityService.saveActivity(any(Activity.class))).thenReturn(activity);
        when(passengerService.savePassenger(any(Passenger.class))).thenReturn(passenger);

        boolean signUpSuccess = activitySignUpService.signUp(passengerId, activityId);

        verify(passengerService, times(1)).savePassenger(passengerCaptor.capture());
        verify(activityService, times(1)).saveActivity(activityCaptor.capture());

        Passenger updatedPassenger = passengerCaptor.getValue();
        Activity updatedActivity = activityCaptor.getValue();

        assertTrue(signUpSuccess);
        assertEquals(0.0, updatedPassenger.getBalance());
        assertEquals(199, updatedActivity.getCapacity());
    }

    @Test
    void testSignUp_InvalidAccountLevel_ExceptionThrown() {
        String passengerId = "passenger4";
        String activityId = "activity4";
        Passenger passenger = new Passenger(passengerId, "Passenger 4", "Rakesh", AccountLevelType.STANDARD, 0.0, new ArrayList<>());
        Activity activity = new Activity(activityId, "Activity 4", "Description 4", 50.0, 100, 50, "destination4");

        when(passengerService.getPassengerById(passengerId)).thenReturn(passenger);
        when(activityService.getActivityById(activityId)).thenReturn(activity);

        assertThrows(IllegalStateException.class, () -> activitySignUpService.signUp(passengerId, activityId));
    }

    @Test
    void testSignUp_InsufficientBalance_StandardPassenger() {
        String passengerId = "passenger5";
        String activityId = "activity5";
        Passenger passenger = new Passenger(passengerId, "Passenger 5", "Rakesh", AccountLevelType.STANDARD, 20.0, new ArrayList<>());
        Activity activity = new Activity(activityId, "Activity 5", "Description 5", 30.0, 100, 50, "destination5");

        when(passengerService.getPassengerById(passengerId)).thenReturn(passenger);
        when(activityService.getActivityById(activityId)).thenReturn(activity);

        boolean signUpSuccess = activitySignUpService.signUp(passengerId, activityId);

        assertFalse(signUpSuccess);
        assertEquals(20.0, passenger.getBalance());
        assertEquals(50, activity.getRemainingCapacity());
        assertFalse(passenger.getActivityIds().contains(activityId));
    }
}