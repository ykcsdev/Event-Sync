package com.ibm.eventsync.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/OpenAPI Configuration for API Documentation
 * Access the API documentation at: /swagger-ui.html or /swagger-ui/
 * OpenAPI JSON at: /v3/api-docs
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "EventSync API",
        version = "1.0.0",
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080",
            description = "Development Server"
        ),
        @Server(
            url = "${swagger.server.url:http://localhost:8080}",
            description = "Production Server"
        )
    }
)
public class SwaggerConfig {
}
