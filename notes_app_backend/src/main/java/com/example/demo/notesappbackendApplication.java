package com.example.notesappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PUBLIC_INTERFACE
 * Entry point for the Notes App Backend.
 * Starts the Spring Boot application that provides REST APIs for managing notes with optional user scoping.
 */
@SpringBootApplication
public class notesappbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(notesappbackendApplication.class, args);
	}
}
