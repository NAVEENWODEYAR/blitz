package com.gowri.blitz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * @author NaveenWodeyar
 * @date 24-02-2025
 */

@RestController
@RequestMapping("/v1/")
public class TestController {

    @GetMapping("/datetime")
    public String getDateTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time to match the required pattern: MondayFeb2025 21:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE MMMMuuuu HH:mm");

        // Return the formatted date and time as a string
        return "Current Date & Time: " + now.format(formatter);
    }
}
