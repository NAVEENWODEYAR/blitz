package com.gowri.blitz.consumers;

import com.google.gson.Gson;
import com.gowri.blitz.modal.Student;
import com.gowri.blitz.service.impl.StudentImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/*
 * @author NaveenWodeyar
 * @date 13-03-2025
 */

@Component
public class KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private StudentImpl studentImpl;

    @Value("${kafka.consumer.test}")
    String topic;

    @KafkaListener(topics = "my_topic", groupId = "my-group")
    public void listen(String message) {
        try {
            log.info("Received message: {}", message);
            Student student = new Gson().fromJson(message, Student.class);
            try{
                studentImpl.addStudent(student);
            }catch (Exception e){
                log.error("Exception occured while savig student:{}",e.getMessage());
            }
            System.out.println("Message consumed successfully: " + message);
            log.debug("Student :{}",student);
        } catch (Exception e) {
            log.error("Error deserializing message: {}", message, e);
        }

    }
}
