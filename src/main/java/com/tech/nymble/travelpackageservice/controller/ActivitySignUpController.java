package com.tech.nymble.travelpackageservice.controller;

import com.tech.nymble.travelpackageservice.model.activity.ActivitySignUpRequest;
import com.tech.nymble.travelpackageservice.service.ActivitySignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sign-up")
public class ActivitySignUpController {
    private final ActivitySignUpService activitySignUpService;

    public ActivitySignUpController(ActivitySignUpService activitySignUpService) {
        this.activitySignUpService = activitySignUpService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody ActivitySignUpRequest request) {

        String passengerId = request.getPassengerId();
        String activityId = request.getActivityId();

        // Call the signUp method in the service
        boolean signUpSuccess = activitySignUpService.signUp(passengerId, activityId);

        if (signUpSuccess) {
            return ResponseEntity.ok("Sign-up successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sign-up failed");
        }
//        TODO: need better error handling for exceptional scenarios
    }
}
