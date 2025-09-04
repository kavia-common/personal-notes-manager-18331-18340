package com.example.notesappbackend.model;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * Represents a Note entity stored in the database.
 */
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Optional owner identifier (e.g. username or userId). If null, note is global/public.
     */
    @Column(name = "owner_id")
    private String ownerId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Note() {
    }

    public Note(String ownerId, String title, String content) {
        this.ownerId = ownerId;
        this.title = title;
        this.content = content;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Note setOwnerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Note setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Note setContent(String content) {
        this.content = content;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
