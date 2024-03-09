package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.destination.Destination;
import com.tech.nymble.travelpackageservice.repository.DestinationQueryRepository;
import com.tech.nymble.travelpackageservice.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationQueryRepository destinationQueryRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository,
                                  DestinationQueryRepository destinationQueryRepository) {
        this.destinationRepository = destinationRepository;
        this.destinationQueryRepository = destinationQueryRepository;
    }

    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination getDestinationById(String id) {
        return destinationRepository.findById(id).orElse(null);
    }

    @Override
    public Destination saveDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public void deleteDestinationById(String id) {
        destinationRepository.deleteById(id);
    }

    @Override
    public List<Destination> getDestinationsByIds(List<String> destinationIds) {
        return destinationQueryRepository.getDestinationsByIds(destinationIds);
    }
}

