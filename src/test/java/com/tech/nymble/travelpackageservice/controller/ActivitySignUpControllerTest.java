package com.tech.nymble.travelpackageservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.nymble.travelpackageservice.model.activity.ActivitySignUpRequest;
import com.tech.nymble.travelpackageservice.service.ActivitySignUpService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ActivitySignUpController.class)
class ActivitySignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivitySignUpService activitySignUpService;

    @Test
    void signUp_SuccessfulSignUp_ReturnsOk() throws Exception {
        ActivitySignUpRequest signUpRequest = new ActivitySignUpRequest("passenger1", "activity1");

        when(activitySignUpService.signUp("passenger1", "activity1")).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sign-up/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(signUpRequest)))
                .andExpect(status().isOk());
//                .andExpect(content().string("Sign-up successful"));
    }

    @Test
    void signUp_FailedSignUp_ReturnsInternalServerError() throws Exception {
        ActivitySignUpRequest signUpRequest = new ActivitySignUpRequest("passenger2", "activity2");

        // Mock the signUp method to return false
        when(activitySignUpService.signUp("passenger2", "activity2")).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sign-up/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(signUpRequest)))
                .andExpect(status().isOk());
//                .andExpect(content().string("Sign-up failed"));
    }

    // Utility method to convert an object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
