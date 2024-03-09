package com.tech.nymble.travelpackageservice.model.travel_package;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("packages")
@AllArgsConstructor
@NoArgsConstructor
public class TravelPackage {
    @Id
    private String id;
    private String name;
    private int maxPassengerCapacity;
    private int passengerCapacity;
    private List<String> destinationIds;
    private List<String> passengerIds;
}