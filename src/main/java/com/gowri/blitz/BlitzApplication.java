package com.gowri.blitz;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class BlitzApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(BlitzApplication.class);
    @Autowired
    private Environment environment;
    private final ApplicationContext context;
    private long applicationStartTime;

    public BlitzApplication(ApplicationContext context) {
        this.context = context;
    }

    @PostConstruct
    public void init() {
        applicationStartTime = System.currentTimeMillis();
    }

    @PreDestroy
    public void onShutdown() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddEEEE HH:mm");
        String formattedDate = LocalDateTime.now().format(formatter);
        log.info("Service is shutting down: {}", formattedDate);
    }

    public static void main(String[] args) {
        SpringApplication.run(BlitzApplication.class, args);
        log.warn("Application started");
    }

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddEEEE HH:mm");
        String formattedDate = LocalDateTime.now().format(formatter);
        log.info("Application Running Successfully: {}", formattedDate);

        // Fetch application name and port from environment variables
        String appName = environment.getProperty("spring.application.name", "DefaultAppName");
        String port = environment.getProperty("server.port", "8080");  // Default to 8080 if not set

        // Calculate the startup time
        long startupTime = System.currentTimeMillis() - applicationStartTime;

        log.info("Application name:{}", appName);
        log.info("Server Port: {}", port);
        log.info("Application Startup Time: {} ms", startupTime);
    }
}
