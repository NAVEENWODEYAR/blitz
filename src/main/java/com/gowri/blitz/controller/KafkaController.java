package com.gowri.blitz.controller;

/*
 * @author NaveenWodeyar
 * @date 13-03-2025
 */

import com.gowri.blitz.producers.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class KafkaController {
    private static final Logger log = LoggerFactory.getLogger(KafkaController.class);

    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        log.info("Received message: {}",message);
        producerService.sendMessage(message);
        return "Message sent to Kafka topic";
    }
}
