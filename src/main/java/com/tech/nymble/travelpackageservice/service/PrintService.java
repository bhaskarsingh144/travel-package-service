package com.tech.nymble.travelpackageservice.service;

public interface PrintService {
    void printTravelPackageItinerary(String travelPackageId);

    void printTravelPackagePassengerList(String travelPackageId);

    void printIndividualPassengerDetails(String passengerId);

    void printAvailableActivities();
}
