package com.example.notesappbackend.service.impl;

import com.example.notesappbackend.dto.NoteRequest;
import com.example.notesappbackend.model.Note;
import com.example.notesappbackend.repository.NoteRepository;
import com.example.notesappbackend.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of NoteService.
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Note create(String ownerId, NoteRequest request) {
        Note note = new Note(ownerId, request.getTitle(), request.getContent());
        return repository.save(note);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Note> getById(String ownerId, Long id) {
        return repository.findById(id)
                .filter(n -> ownerId == null || ownerId.equals(n.getOwnerId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getAll(String ownerId) {
        if (ownerId == null) {
            return repository.findAll();
        }
        return repository.findByOwnerId(ownerId);
    }

    @Override
    public Optional<Note> update(String ownerId, Long id, NoteRequest request) {
        Optional<Note> existingOpt = getById(ownerId, id);
        if (existingOpt.isEmpty()) {
            return Optional.empty();
        }
        Note existing = existingOpt.get();
        if (request.getTitle() != null) {
            existing.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            existing.setContent(request.getContent());
        }
        return Optional.of(repository.save(existing));
    }

    @Override
    public boolean delete(String ownerId, Long id) {
        Optional<Note> existing = getById(ownerId, id);
        if (existing.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
