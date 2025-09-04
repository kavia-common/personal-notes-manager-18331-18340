package com.example.notesappbackend.repository;

import com.example.notesappbackend.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for accessing persisted notes.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByOwnerId(String ownerId);
}
