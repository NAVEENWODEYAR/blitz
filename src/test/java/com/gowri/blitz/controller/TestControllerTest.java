package com.gowri.blitz.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/*
 * @author NaveenWodeyar
 * @date 24-02-2025
 */class TestControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testGetDateTime() throws Exception {
            // Perform a GET request to the /v1/datetime endpoint
            mockMvc.perform(get("/v1/datetime"))
                    .andExpect(MockMvcResultMatchers.status().isOk()) // Assert HTTP 200 OK
                    .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Current Date & Time:"))); // Assert that the response contains the string
        }

}