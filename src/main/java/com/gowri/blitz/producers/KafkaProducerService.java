package com.gowri.blitz.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/*
 * @author NaveenWodeyar
 * @date 13-03-2025
 */
@Component
public class KafkaProducerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.producer.test}")
    String topic;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a message to Kafka with retry mechanism.
     * @param message The message to send to Kafka.
     */
    @Retryable(
            value = {Exception.class},  // Retry on any exception (you can narrow this down if needed)
            maxAttempts = 3,            // Max retry attempts (e.g., 3 retries)
            backoff = @Backoff(delay = 2000, multiplier = 1.5)  // 2 seconds delay with exponential backoff
    )
    public void sendMessage(String message) {
        try {
            log.info("Sending message: {}", message);
            kafkaTemplate.send(topic, message);  // Send the message to the specified Kafka topic
        } catch (Exception e) {
            log.error("Exception occurred while sending message to Kafka: {}", e.getMessage());
            throw e;  // Rethrow the exception to trigger retry logic
        }
    }
}
