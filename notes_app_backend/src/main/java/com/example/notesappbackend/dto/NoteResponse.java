package com.example.notesappbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * PUBLIC_INTERFACE
 * Response model for a note resource.
 */
@Schema(description = "Note response object")
public class NoteResponse {

    @Schema(description = "Note ID", example = "1")
    private Long id;

    @Schema(description = "Owner identifier, if provided", example = "user123")
    private String ownerId;

    @Schema(description = "Note title", example = "My first note")
    private String title;

    @Schema(description = "Note content", example = "This is the content")
    private String content;

    @Schema(description = "Creation timestamp in ISO-8601", example = "2024-01-01T12:00:00Z")
    private Instant createdAt;

    @Schema(description = "Last update timestamp in ISO-8601", example = "2024-01-01T12:30:00Z")
    private Instant updatedAt;

    public NoteResponse() {}

    public NoteResponse(Long id, String ownerId, String title, String content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
