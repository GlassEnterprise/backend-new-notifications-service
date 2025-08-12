package com.retailx.messaging;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RetailX Messaging Hub Application
 * 
 * Modern, cloud-native notification service designed to replace the legacy
 * foo-legacy-notifications-api. This service provides enhanced features including:
 * 
 * MODERN FEATURES:
 * - Rich content support (HTML, templates, attachments)
 * - Advanced delivery tracking and analytics
 * - Rate limiting and throttling
 * - Multi-channel messaging (email, SMS, push, in-app)
 * - Template management and personalization
 * - Delivery scheduling and batching
 * - Webhook callbacks for delivery status
 * 
 * MIGRATION GOALS:
 * - Replace legacy notification endpoints with modern equivalents
 * - Provide backward compatibility during transition period
 * - Implement proper error handling and retry mechanisms
 * - Add comprehensive monitoring and observability
 * - Support for horizontal scaling and high availability
 */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "RetailX Messaging Hub API",
        version = "2.0.0",
        description = "Modern notification service for the RetailX platform. " +
                     "Designed to replace the legacy foo-legacy-notifications-api with enhanced features " +
                     "including rich content, advanced tracking, and multi-channel delivery.",
        contact = @Contact(
            name = "RetailX Platform Team",
            email = "platform-team@retailx.com"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8084", description = "Local development server"),
        @Server(url = "https://api.retailx.com/messaging", description = "Production server")
    }
)
public class MessagingHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingHubApplication.class, args);
    }
}
