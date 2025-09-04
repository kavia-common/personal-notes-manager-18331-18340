package com.example.notesappbackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * PUBLIC_INTERFACE
 * Configures OpenAPI metadata and tags.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI appOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Notes App Backend API")
                        .version("0.1.0")
                        .description("RESTful API for creating, reading, updating, and deleting notes. Supports optional user scoping via header X-User-Id.")
                        .contact(new Contact().name("Notes App").email("support@example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger UI")
                        .url("/swagger-ui.html"))
                .tags(List.of(
                        new Tag().name("Notes").description("CRUD operations on notes"),
                        new Tag().name("Auth (Demo)").description("Demo endpoints for header-based user scoping"),
                        new Tag().name("Utility").description("Utility endpoints")
                ));
    }
}
