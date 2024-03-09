package com.tech.nymble.travelpackageservice.model.destination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("destinations")
@AllArgsConstructor
@NoArgsConstructor
public class Destination {
    @Id
    private String id;
    private String name;
    private List<String> activityIds;
}
