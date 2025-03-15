package com.gowri.blitz.consumers;

/*
 * @author NaveenWodeyar
 * @date 13-03-2025
 */

import com.google.gson.Gson;
import com.gowri.blitz.modal.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "my_topic", groupId = "my-group")
    public void listen(String message) {
        try {
            log.info("Received message: {}", message);
            Student student = new Gson().fromJson(message, Student.class);
            System.out.println("Message consumed successfully: " + message);
        } catch (Exception e) {
            log.error("Error deserializing message: {}", message, e);
        }

    }
}
