package com.tech.nymble.travelpackageservice.service;

import com.tech.nymble.travelpackageservice.model.passenger.Passenger;
import com.tech.nymble.travelpackageservice.repository.PassengerQueryRepository;
import com.tech.nymble.travelpackageservice.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerQueryRepository passengerQueryRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository,
                                PassengerQueryRepository passengerQueryRepository) {
        this.passengerRepository = passengerRepository;
        this.passengerQueryRepository = passengerQueryRepository;
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassengerById(String id) {
        return passengerRepository.findById(id).orElse(null);
    }

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public void deletePassengerById(String id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public List<Passenger> getPassengersByIds(List<String> passengerIds) {
        return passengerQueryRepository.getTravelPackagesByIds(passengerIds);
    }
}

