package com.gowri.blitz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
@EnableCaching
public class BlitzApplication {
	private static final Logger log = LoggerFactory.getLogger(BlitzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BlitzApplication.class, args);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddEEEE HH:mm");
		String formattedDate = LocalDateTime.now().format(formatter);
		log.info("Application running successfully: {}", formattedDate);
	}

}
