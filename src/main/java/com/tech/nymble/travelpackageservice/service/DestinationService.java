package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.destination.Destination;

import java.util.List;

public interface DestinationService {

    List<Destination> getAllDestinations();

    Destination getDestinationById(String id);

    Destination saveDestination(Destination destination);

    void deleteDestinationById(String id);

    List<Destination> getDestinationsByIds(List<String> destinationIds);
}

