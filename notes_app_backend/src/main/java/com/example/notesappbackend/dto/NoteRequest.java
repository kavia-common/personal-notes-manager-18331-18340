package com.example.notesappbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PUBLIC_INTERFACE
 * Request body for creating/updating a note.
 */
@Schema(description = "Payload to create or update a note")
public class NoteRequest {

    @Schema(description = "Note title", example = "My first note", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Note content body", example = "This is the content of my note", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String content;

    public NoteRequest() {}

    public NoteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public NoteRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NoteRequest setContent(String content) {
        this.content = content;
        return this;
    }
}
