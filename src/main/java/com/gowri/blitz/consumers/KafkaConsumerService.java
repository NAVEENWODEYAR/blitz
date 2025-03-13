package com.gowri.blitz.consumers;

/*
 * @author NaveenWodeyar
 * @date 13-03-2025
 */

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @KafkaListener(topics = "my_topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
