package com.tech.nymble.travelpackageservice.model.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySignUpRequest {
    private String passengerId;
    private String activityId;
}