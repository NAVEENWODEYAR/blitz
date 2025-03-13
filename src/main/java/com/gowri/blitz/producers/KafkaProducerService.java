package com.gowri.blitz.producers;

/*
 * @author NaveenWodeyar
 * @date 13-03-2025
 */

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("my_topic", message);  // "my_topic" is the Kafka topic to which we send messages
    }
}
