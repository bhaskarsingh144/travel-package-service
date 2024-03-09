package com.tech.nymble.travelpackageservice.controller;

import com.tech.nymble.travelpackageservice.service.PrintService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PrintController.class)
class PrintControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PrintService printService;

    @Test
    void testPrintTravelPackageItinerary() throws Exception {
        String travelPackageId = "123";

        mockMvc.perform(get("/api/v1/print/itinerary/{travelPackageId}", travelPackageId))
                .andExpect(status().isOk());

        verify(printService, times(1)).printTravelPackageItinerary(eq(travelPackageId));
    }

    @Test
    void testPrintTravelPackagePassengerList() throws Exception {
        String travelPackageId = "123";

        mockMvc.perform(get("/api/v1/print/passenger-list/{travelPackageId}", travelPackageId))
                .andExpect(status().isOk());

        verify(printService, times(1)).printTravelPackagePassengerList(eq(travelPackageId));
    }

    @Test
    void testPrintIndividualPassengerDetails() throws Exception {
        String passengerId = "456";

        mockMvc.perform(get("/api/v1/print/passenger-details/{passengerId}", passengerId))
                .andExpect(status().isOk());

        verify(printService, times(1)).printIndividualPassengerDetails(eq(passengerId));
    }

    @Test
    void testPrintAvailableActivities() throws Exception {
        mockMvc.perform(get("/api/v1/print/available-activities"))
                .andExpect(status().isOk());

        verify(printService, times(1)).printAvailableActivities();
    }
}
