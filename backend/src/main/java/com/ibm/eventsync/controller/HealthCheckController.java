package com.ibm.eventsync.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * Health Check Controller for monitoring and readiness probes
 */
@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    @Value("${spring.application.name:eventsync}")
    private String applicationName;

    @Value("${server.port:8080}")
    private String serverPort;

    /**
     * Basic health check endpoint
     * Returns 200 OK with status information
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", applicationName);
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    /**
     * Liveness probe - checks if application is running
     * Used by container orchestration for restart decisions
     */
    @GetMapping("/live")
    public ResponseEntity<Map<String, String>> liveness() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "ALIVE");
        return ResponseEntity.ok(response);
    }

    /**
     * Readiness probe - checks if application is ready to accept traffic
     * Used by container orchestration for routing decisions
     */
    @GetMapping("/ready")
    public ResponseEntity<Map<String, String>> readiness() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "READY");
        return ResponseEntity.ok(response);
    }
}
