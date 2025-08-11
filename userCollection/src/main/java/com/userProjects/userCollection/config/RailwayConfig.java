package com.userProjects.userCollection.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class RailwayConfig {
    // Minimal configuration for Railway deployment
    // This ensures the application can start without database dependencies
}
