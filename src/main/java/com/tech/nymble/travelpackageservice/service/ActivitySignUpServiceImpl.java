package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.activity.Activity;
import com.tech.nymble.travelpackageservice.model.passenger.Passenger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ActivitySignUpServiceImpl implements ActivitySignUpService {

    private final ActivityService activityService;
    private final PassengerService passengerService;


    public ActivitySignUpServiceImpl(ActivityService activityService, PassengerService passengerService) {
        this.activityService = activityService;
        this.passengerService = passengerService;
    }

    /**
     * Signs up a passenger for an activity based on their account level.
     *
     * @param passengerId The id of passenger signing up for the activity.
     * @param activityId  The id of activity for which the passenger is signing up.
     * @return True if the sign-up is successful, false otherwise.
     * @throws IllegalStateException If the passenger has an invalid account level.
     */
    @Override
    public boolean signUp(String passengerId, String activityId) {

        Passenger passenger = passengerService.getPassengerById(passengerId);
        Activity activity = activityService.getActivityById(activityId);

        if (Objects.isNull(passenger) || Objects.isNull(activity)) {
            throw new IllegalArgumentException("Passenger or Activity not found");
        }
        boolean signUpSuccess = false;

        switch (passenger.getAccountLevelType()) {
            case STANDARD -> signUpSuccess = signUpStandardPassenger(passenger, activity);
            case GOLD -> signUpSuccess = signUpGoldPassenger(passenger, activity);
            case PREMIUM -> signUpSuccess = signUpPremiumPassenger(passenger, activity);
            default -> {
                String errMsg = String.format("Insufficient balance for Standard Passenger. SerialNo: %s, Activity: %s",
                        passenger.getPassengerNumber(), activity.getName());
                log.error(errMsg);
                throw new IllegalStateException(errMsg);
            }
        }
        return signUpSuccess;
    }

    /**
     * Signs up a standard passenger for an activity.
     *
     * @param passenger The standard passenger signing up.
     * @param activity  The activity for which the passenger is signing up.
     * @return True if the sign-up is successful, false otherwise.
     */
    private boolean signUpStandardPassenger(Passenger passenger, Activity activity) {
        if (isBalanceSufficient(passenger, activity)) {
            passenger.setBalance(passenger.getBalance() - activity.getCost());
            return updatePassengerAndActivityRecord(passenger, activity);
        } else {
            log.error("Insufficient balance for Standard Passenger. SerialNo: {}, Activity: {}",
                    passenger.getPassengerNumber(), activity.getName());
            return false;
        }
    }

    /**
     * Signs up a gold passenger for an activity.
     *
     * @param passenger The gold passenger signing up.
     * @param activity  The activity for which the passenger is signing up.
     * @return True if the sign-up is successful, false otherwise.
     */
    private boolean signUpGoldPassenger(Passenger passenger, Activity activity) {
        if (isBalanceSufficient(passenger, activity)) {
            // Discount 10%
            double discountedCost = activity.getCost() * 0.9;
            passenger.setBalance(passenger.getBalance() - discountedCost);
            return updatePassengerAndActivityRecord(passenger, activity);
        } else {
            log.error("Insufficient balance for Gold Passenger. SerialNo: {}, Activity: {}",
                    passenger.getPassengerNumber(), activity.getName());
            return false;
        }
    }

    /**
     * Signs up a premium passenger for an activity.
     *
     * @param passenger The premium passenger signing up.
     * @param activity  The activity for which the passenger is signing up.
     * @return True if the sign-up is successful, false otherwise.
     */
    private boolean signUpPremiumPassenger(Passenger passenger, Activity activity) {
        return updatePassengerAndActivityRecord(passenger, activity);
    }

    /**
     * Checks if a passenger has sufficient balance for signing up.
     *
     * @param passenger The passenger attempting to sign up.
     * @param activity  The activity for which the passenger is signing up.
     * @return True if the passenger has sufficient balance, false otherwise.
     */
    private boolean isBalanceSufficient(Passenger passenger, Activity activity) {
        return passenger.getBalance() >= activity.getCost();
    }

    /**
     * Updates passenger and activity records in the database after a successful sign-up.
     *
     * @param passenger The passenger who signed up.
     * @param activity  The activity for which the passenger signed up.
     * @return True if the database update is successful, false otherwise.
     */
    private boolean updatePassengerAndActivityRecord(Passenger passenger, Activity activity) {
        try {
            // Attach activity id to passenger
            passenger.getActivityIds().add(activity.getId());
            // Adjust activity capacity
            activity.setRemainingCapacity(activity.getCapacity() - 1);
            // Update passenger and activity record in the database
            passengerService.savePassenger(passenger);
            activityService.saveActivity(activity);
            return true;
        } catch (Exception e) {
            log.error("Database update failed for Passenger: {}, Activity: {}", passenger.getId(), activity.getId(), e);
            return false;
        }
    }
}
