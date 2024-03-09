package com.tech.nymble.travelpackageservice.model.passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("passengers")
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    private String id;
    private String passengerNumber;
    private String name;
    private AccountLevelType accountLevelType; // enum: Gold, Standard, Premium
    private double balance;
    private List<String> activityIds;

//    public Passenger(String s, String john_doe, String standard, double v) {
//    }
}

