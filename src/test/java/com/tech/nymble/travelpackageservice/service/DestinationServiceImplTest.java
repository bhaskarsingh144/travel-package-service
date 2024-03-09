package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.destination.Destination;
import com.tech.nymble.travelpackageservice.repository.DestinationQueryRepository;
import com.tech.nymble.travelpackageservice.repository.DestinationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DestinationServiceImplTest {

    @InjectMocks
    private DestinationServiceImpl destinationService;
    @Mock
    private DestinationRepository destinationRepository;
    @Mock
    private DestinationQueryRepository destinationQueryRepository;

    @Test
    void testGetAllDestinations() {
        List<Destination> expectedDestinations = Arrays.asList(
                new Destination("destination1", "Destination 1", new ArrayList<>()),
                new Destination("destination2", "Destination 2", new ArrayList<>())
        );

        when(destinationRepository.findAll()).thenReturn(expectedDestinations);

        List<Destination> actualDestinations = destinationService.getAllDestinations();

        verify(destinationRepository, times(1)).findAll();
        assertEquals(expectedDestinations, actualDestinations);
    }

    @Test
    void testGetDestinationById() {
        String destinationId = "destination1";
        Destination expectedDestination = new Destination(destinationId, "Destination 1", new ArrayList<>());

        when(destinationRepository.findById(destinationId)).thenReturn(Optional.of(expectedDestination));

        Destination actualDestination = destinationService.getDestinationById(destinationId);

        verify(destinationRepository, times(1)).findById(destinationId);
        assertEquals(expectedDestination, actualDestination);
    }

    @Test
    void testSaveDestination() {
        Destination destinationToSave = new Destination("destination1", "Destination 1", new ArrayList<>());
        Destination expectedSavedDestination = new Destination("destination1", "Destination 1", new ArrayList<>());

        when(destinationRepository.save(destinationToSave)).thenReturn(expectedSavedDestination);

        Destination actualSavedDestination = destinationService.saveDestination(destinationToSave);

        verify(destinationRepository, times(1)).save(destinationToSave);
        assertEquals(expectedSavedDestination, actualSavedDestination);
    }

    @Test
    void testDeleteDestinationById() {
        String destinationId = "destination1";

        destinationService.deleteDestinationById(destinationId);

        verify(destinationRepository, times(1)).deleteById(destinationId);
    }

    @Test
    void testGetDestinationsByIds() {
        List<String> destinationIds = Arrays.asList("destination1", "destination2");
        List<Destination> expectedDestinations = Arrays.asList(
                new Destination("destination1", "Destination 1", new ArrayList<>()),
                new Destination("destination2", "Destination 2", new ArrayList<>())
        );

        when(destinationQueryRepository.getDestinationsByIds(destinationIds)).thenReturn(expectedDestinations);

        List<Destination> actualDestinations = destinationService.getDestinationsByIds(destinationIds);

        verify(destinationQueryRepository, times(1)).getDestinationsByIds(destinationIds);
        assertEquals(expectedDestinations, actualDestinations);
    }
}
