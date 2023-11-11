package dev.pragati.paymentmanagementservice.controllers;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;

    @Override
    public Health health() {
        if(databaseIsUp()) {
            return Health.up().withDetail("message", "Database is up").build();
        } else {
            return Health.down().withDetail("message", "Database is down").build();
        }
    }

    private boolean databaseIsUp() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, username, null);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
