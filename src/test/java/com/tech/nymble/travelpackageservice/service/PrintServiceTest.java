package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.activity.Activity;
import com.tech.nymble.travelpackageservice.model.destination.Destination;
import com.tech.nymble.travelpackageservice.model.passenger.AccountLevelType;
import com.tech.nymble.travelpackageservice.model.passenger.Passenger;
import com.tech.nymble.travelpackageservice.model.travel_package.TravelPackage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PrintServiceTest {

    @InjectMocks
    private PrintServiceImpl printService;
    @Mock
    private TravelPackageService travelPackageService;
    @Mock
    private ActivityService activityService;
    @Mock
    private PassengerService passengerService;
    @Mock
    private DestinationService destinationService;

    @Test
    void testPrintTravelPackageItinerary() {
        String travelPackageId = "123";
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId(travelPackageId);
        travelPackage.setName("Test Package");
        travelPackage.setDestinationIds(Arrays.asList("dest1", "dest2"));

        Destination destination1 = new Destination();
        destination1.setName("Destination 1");
        destination1.setActivityIds(Arrays.asList("activity1", "activity2"));

        Destination destination2 = new Destination();
        destination2.setName("Destination 2");
        destination2.setActivityIds(Arrays.asList("activity3", "activity4"));

        Activity activity1 = new Activity();
        activity1.setName("Activity 1");
        activity1.setDescription("Description 1");
        activity1.setCost(50.0);
        activity1.setCapacity(10);
        activity1.setRemainingCapacity(5);

        Activity activity2 = new Activity();
        activity2.setName("Activity 2");
        activity2.setDescription("Description 2");
        activity2.setCost(30.0);
        activity2.setCapacity(8);
        activity2.setRemainingCapacity(3);

        when(travelPackageService.getTravelPackageById(travelPackageId)).thenReturn(travelPackage);
        when(destinationService.getDestinationsByIds(travelPackage.getDestinationIds()))
                .thenReturn(Arrays.asList(destination1, destination2));
        when(activityService.getActivitiesByIds(destination1.getActivityIds())).thenReturn(Arrays.asList(activity1, activity2));
        when(activityService.getActivitiesByIds(destination2.getActivityIds())).thenReturn(Collections.emptyList());

        printService.printTravelPackageItinerary(travelPackageId);
    }

    @Test
    void testPrintTravelPackagePassengerList() {
        String travelPackageId = "123";
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setMaxPassengerCapacity(10);
        travelPackage.setPassengerCapacity(5);
        Passenger passenger = new Passenger();
        List<String> passengerIds = Arrays.asList("1", "2", "3");
        List<Passenger> passengers = Arrays.asList(
                new Passenger("1", "John Doe", "Standard", AccountLevelType.GOLD, 0.0, new ArrayList<>()),
                new Passenger("2", "Jane Doe", "Gold", AccountLevelType.GOLD, 0.0, new ArrayList<>()),
                new Passenger("3", "Bob Smith", "Premium", AccountLevelType.GOLD, 0.0, new ArrayList<>())
        );
        when(travelPackageService.getTravelPackageById(anyString())).thenReturn(travelPackage);
        when(passengerService.getPassengersByIds(anyList())).thenReturn(passengers);

        printService.printTravelPackagePassengerList(travelPackageId);

        verify(travelPackageService, times(1)).getTravelPackageById(anyString());
        verify(passengerService, times(1)).getPassengersByIds(anyList());
    }

    @Test
    void testPrintIndividualPassengerDetails() {
        String passengerId = "1";
        Passenger passenger = new Passenger("1", "John Doe", "Standard", AccountLevelType.GOLD, 89.3, new ArrayList<>());
        List<String> activityIds = Arrays.asList("activity1", "activity2");
        List<Activity> activities = Arrays.asList(
                new Activity("activity1", "Activity 1", "Description 1", 50.0, 10, 10, "destination1"),
                new Activity("activity2", "Activity 2", "Description 2", 30.0, 5, 5, "destination2")
        );

        when(passengerService.getPassengerById(passengerId)).thenReturn(passenger);
        when(activityService.getActivitiesByIds(activityIds)).thenReturn(activities);
        when(destinationService.getDestinationById("destination1")).thenReturn(new Destination("destination1", "Destination 1", null));
        when(destinationService.getDestinationById("destination2")).thenReturn(new Destination("destination2", "Destination 2", null));

        printService.printIndividualPassengerDetails(passengerId);

        verify(passengerService, times(1)).getPassengerById(passengerId);
        verify(activityService, times(1)).getActivitiesByIds(activityIds);
        verify(destinationService, times(2)).getDestinationById(anyString());
    }

    @Test
    void testPrintAvailableActivities() {
        List<Activity> allActivities = Arrays.asList(
                new Activity("activity1", "Activity 1", "Description 1", 50.0, 10, 5, "destination1"),
                new Activity("activity2", "Activity 2", "Description 2", 30.0, 5, 0, "destination2")
        );

        when(activityService.getAllActivities()).thenReturn(allActivities);
        printService.printAvailableActivities();
        verify(activityService, times(1)).getAllActivities();
    }
}
