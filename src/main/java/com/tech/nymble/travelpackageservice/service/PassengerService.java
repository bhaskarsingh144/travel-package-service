package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.passenger.Passenger;

import java.util.List;

public interface PassengerService {

    List<Passenger> getAllPassengers();

    Passenger getPassengerById(String id);

    Passenger savePassenger(Passenger passenger);

    void deletePassengerById(String id);

    List<Passenger> getPassengersByIds(List<String> passengerIds);
}
