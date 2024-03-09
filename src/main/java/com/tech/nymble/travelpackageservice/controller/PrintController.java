package com.tech.nymble.travelpackageservice.controller;

import com.tech.nymble.travelpackageservice.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/print")
public class PrintController {

    private final PrintService printService;

    @Autowired
    public PrintController(PrintService printService) {
        this.printService = printService;
    }

    @GetMapping("/itinerary/{travelPackageId}")
    public void printTravelPackageItinerary(@PathVariable String travelPackageId) {
        printService.printTravelPackageItinerary(travelPackageId);
    }

    @GetMapping("/passenger-list/{travelPackageId}")
    public void printTravelPackagePassengerList(@PathVariable String travelPackageId) {
        printService.printTravelPackagePassengerList(travelPackageId);
    }

    @GetMapping("/passenger-details/{passengerId}")
    public void printIndividualPassengerDetails(@PathVariable String passengerId) {
        printService.printIndividualPassengerDetails(passengerId);
    }

    @GetMapping("/available-activities")
    public void printAvailableActivities() {
        printService.printAvailableActivities();
    }
}
