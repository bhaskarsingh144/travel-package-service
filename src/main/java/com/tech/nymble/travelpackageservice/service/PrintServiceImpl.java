package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.activity.Activity;
import com.tech.nymble.travelpackageservice.model.destination.Destination;
import com.tech.nymble.travelpackageservice.model.passenger.Passenger;
import com.tech.nymble.travelpackageservice.model.travel_package.TravelPackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service implementation for printing travel package-related information.
 */
@Service
@Slf4j
public class PrintServiceImpl implements PrintService {

    private final TravelPackageService travelPackageService;
    private final ActivityService activityService;
    private final PassengerService passengerService;
    private final DestinationService destinationService;


    public PrintServiceImpl(TravelPackageService travelPackageService, ActivityService activityService,
                            PassengerService passengerService, DestinationService destinationService) {
        this.travelPackageService = travelPackageService;
        this.activityService = activityService;
        this.passengerService = passengerService;
        this.destinationService = destinationService;
    }

    /**
     * Prints the itinerary of a travel package, including destination details and activity information.
     *
     * @param travelPackageId The ID of the travel package.
     */
    @Override
    public void printTravelPackageItinerary(String travelPackageId) {

        TravelPackage travelPackage = travelPackageService.getTravelPackageById(travelPackageId);

        if (!Objects.isNull(travelPackage)) {
            List<String> packageIds = travelPackage.getDestinationIds();
            List<Destination> destinations = destinationService.getDestinationsByIds(packageIds);
            // activityInfo Mao contains Destination Name as key and List of Activities available there as value
            Map<String, List<Activity>> activityInfo = destinations
                    .stream()
                    .collect(Collectors.toMap(
                            Destination::getName,
                            destination -> activityService.getActivitiesByIds(destination.getActivityIds())
                    ));
//           print destination info with package name and activities
            log.info(travelPackage.getName());
            log.info(activityInfo.toString());
        }
    }

    /**
     * Prints the passenger list of a travel package, including package details and passenger information.
     *
     * @param travelPackageId The ID of the travel package.
     */
    @Override
    public void printTravelPackagePassengerList(String travelPackageId) {
        TravelPackage travelPackage = travelPackageService.getTravelPackageById(travelPackageId);
        var passengerIds = travelPackage.getPassengerIds();
        int remainingCapacity = travelPackage.getMaxPassengerCapacity() - travelPackage.getPassengerCapacity();
        List<Passenger> passengers = passengerService.getPassengersByIds(passengerIds);
        Map<String, String> passengerNameNumberMap = passengers
                .stream()
                .collect(Collectors.toMap(
                        Passenger::getName,
                        Passenger::getPassengerNumber
                ));
        log.info("Current Passenger Capacity: {}", travelPackage.getPassengerCapacity());
        log.info("Remaining Passenger Capacity: {}", remainingCapacity);
        log.info("Passenger Details: /n {}", passengerNameNumberMap);
    }

    /**
     * Prints the details of an individual passenger, including their activities and balances.
     *
     * @param passengerId The ID of the passenger.
     */
    @Override
    public void printIndividualPassengerDetails(String passengerId) {
        Passenger passenger = passengerService.getPassengerById(passengerId);
        String passengerName = passenger.getName();
        String passengerNumber = passenger.getPassengerNumber();
        double passengerBalance = passenger.getBalance();
        List<String> activityIds = passenger.getActivityIds();
        List<Activity> activities = activityService.getActivitiesByIds(activityIds);
        Map<String, Double> activityCostMap = activities
                .stream()
                .collect(Collectors.toMap(
                        Activity::getName,
                        Activity::getCost
                ));

        Map<String, String> activityDestinationMap = activities
                .stream()
                .collect(Collectors.toMap(
                        Activity::getName,
                        activity -> destinationService.getDestinationById(activity.getDestinationId()).getName()
                ));

        log.info("Passenger information - Name: {}, Passenger Number: {}, Balance: {}",
                passengerName, passengerNumber, passengerBalance);
        log.info("Signed Up Activity Cost: {}", activityCostMap);
        log.info("Activity:::Destination View: {}", activityDestinationMap);

    }

    /**
     * Prints the details of activities that still have spaces available in a travel package..
     */
    @Override
    public void printAvailableActivities() {
        // Assuming all activities and not package specific

        var activityNameCapacityMap = activityService.getAllActivities()
                .stream()
                // TODO: Need to write db query instead fetching all and filtering here
                .filter(activity -> activity.getRemainingCapacity() > 0)
                .collect(Collectors.toMap(
                        Activity::getName,
                        Activity::getRemainingCapacity
                ));
        log.info("Available Activity View {}", activityNameCapacityMap);
    }
}
