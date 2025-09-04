package com.example.notesappbackend.service;

import com.example.notesappbackend.dto.NoteRequest;
import com.example.notesappbackend.model.Note;

import java.util.List;
import java.util.Optional;

/**
 * PUBLIC_INTERFACE
 * Service for managing notes.
 */
public interface NoteService {

    /**
     * Create a note for an optional owner.
     */
    Note create(String ownerId, NoteRequest request);

    /**
     * Get a note by ID ensuring owner access where owner scoping is enabled.
     */
    Optional<Note> getById(String ownerId, Long id);

    /**
     * Get all notes for owner if provided, or all notes if owner is null.
     */
    List<Note> getAll(String ownerId);

    /**
     * Update a note by ID with owner scoping.
     */
    Optional<Note> update(String ownerId, Long id, NoteRequest request);

    /**
     * Delete a note by ID with owner scoping.
     */
    boolean delete(String ownerId, Long id);
}
