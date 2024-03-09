package com.tech.nymble.travelpackageservice.model.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("activities")
public class Activity {
    @Id
    private String id;
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private int remainingCapacity;
    private String destinationId;

    public int getCurrentCapacity() {
        return this.capacity - this.remainingCapacity;
    }
}