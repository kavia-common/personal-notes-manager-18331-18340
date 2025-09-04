package com.example.notesappbackend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Simple hello and utility endpoints.
 */
@RestController
@Tag(name = "Utility", description = "Basic utility and documentation endpoints")
public class HelloController {

    /**
     * PUBLIC_INTERFACE
     * Welcome endpoint
     * @return welcome text
     */
    @GetMapping("/")
    @Operation(summary = "Welcome endpoint", description = "Returns a welcome message.")
    public String hello() {
        return "Hello, Spring Boot! Welcome to notesappbackend";
    }

    /**
     * PUBLIC_INTERFACE
     * Redirect to Swagger UI.
     * @return redirect view
     */
    @GetMapping("/docs")
    @Operation(summary = "API Documentation", description = "Redirects to Swagger UI.")
    public RedirectView docs() {
        return new RedirectView("/swagger-ui.html");
    }

    /**
     * PUBLIC_INTERFACE
     * Health check endpoint.
     * @return OK if app is healthy
     */
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status.")
    public String health() {
        return "OK";
    }

    /**
     * PUBLIC_INTERFACE
     * Info endpoint
     * @return information about the application
     */
    @GetMapping("/api/info")
    @Operation(summary = "Application info", description = "Returns application information.")
    public String info() {
        return "Spring Boot Application: notesappbackend";
    }
}