package com.pratha.powerproject.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Pramosh Shrestha
 * @created 03/11/2023: 11:18
 */
@Configuration
@Profile(value = {"development", "local"})
@OpenAPIDefinition(
        info = @Info(
                title = "Power Plant APIs (Development tools)",
                version = "v1.0",
                description = "This app provides REST APIs for Power plant ",
                contact = @Contact(
                        name = "Anonymous Power Plant Inc.",
                        email = "powerplant@anonymous.com",
                        url = "http://www.anynonymous-corp.com"
                ),
                license = @License(name = "Anonymous Power Plant Inc. Copyright (c) 2023"),
                termsOfService = "http://www.powerplant-terms-of-service.com"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080/",
                        description = "Local Server: (Internal team)"
                )
        }
)
public class OpenAPIConfig {
}
