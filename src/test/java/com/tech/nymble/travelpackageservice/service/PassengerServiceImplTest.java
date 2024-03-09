package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.passenger.AccountLevelType;
import com.tech.nymble.travelpackageservice.model.passenger.Passenger;
import com.tech.nymble.travelpackageservice.repository.PassengerQueryRepository;
import com.tech.nymble.travelpackageservice.repository.PassengerRepository;
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
class PassengerServiceImplTest {

    @InjectMocks
    private PassengerServiceImpl passengerService;
    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private PassengerQueryRepository passengerQueryRepository;


    @Test
    void testGetAllPassengers() {
        List<Passenger> expectedPassengers = Arrays.asList(
                new Passenger("passenger1", "P001", "Passenger 1", AccountLevelType.STANDARD, 100.0, new ArrayList<>()),
                new Passenger("passenger2", "P002", "Passenger 2", AccountLevelType.GOLD, 200.0, new ArrayList<>())
        );

        when(passengerRepository.findAll()).thenReturn(expectedPassengers);

        List<Passenger> actualPassengers = passengerService.getAllPassengers();

        verify(passengerRepository, times(1)).findAll();
        assertEquals(expectedPassengers, actualPassengers);
    }

    @Test
    void getPassengerById() {
        Passenger expectedPassenger = new Passenger("passenger1", "P001", "Passenger 1", AccountLevelType.STANDARD, 100.0, new ArrayList<>());
        when(passengerRepository.findById("passenger1")).thenReturn(Optional.of(expectedPassenger));

        Passenger result = passengerService.getPassengerById("passenger1");

        assertEquals(expectedPassenger, result);
    }

    @Test
    void savePassenger() {
        Passenger passengerToSave = new Passenger("passenger1", "P001", "Passenger 1", AccountLevelType.STANDARD, 100.0, new ArrayList<>());
        when(passengerRepository.save(passengerToSave)).thenReturn(passengerToSave);

        Passenger result = passengerService.savePassenger(passengerToSave);

        assertEquals(passengerToSave, result);
    }

    @Test
    void deletePassengerById() {
        passengerService.deletePassengerById("passenger1");

        verify(passengerRepository).deleteById("passenger1");
    }

    @Test
    void getPassengersByIds() {
        List<String> passengerIds = Arrays.asList("passenger1", "passenger2");
        List<Passenger> expectedPassengers = Arrays.asList(
                new Passenger("passenger1", "P001", "Passenger 1", AccountLevelType.STANDARD, 100.0, new ArrayList<>()),
                new Passenger("passenger2", "P002", "Passenger 2", AccountLevelType.GOLD, 200.0, new ArrayList<>())
        );
        when(passengerQueryRepository.getTravelPackagesByIds(passengerIds)).thenReturn(expectedPassengers);

        List<Passenger> result = passengerService.getPassengersByIds(passengerIds);

        assertEquals(expectedPassengers, result);
    }
}
