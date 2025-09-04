package com.example.notesappbackend.web;

import com.example.notesappbackend.dto.NoteRequest;
import com.example.notesappbackend.dto.NoteResponse;
import com.example.notesappbackend.model.Note;
import com.example.notesappbackend.security.AuthUtil;
import com.example.notesappbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PUBLIC_INTERFACE
 * REST Controller providing CRUD operations for notes.
 * Optional user scoping via header X-User-Id.
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "CRUD operations for notes. Optional user scoping via header " + "X-User-Id")
public class NotesController {

    private final NoteService service;

    public NotesController(NoteService service) {
        this.service = service;
    }

    private static NoteResponse toResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getOwnerId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }

    /**
     * PUBLIC_INTERFACE
     * Create a new note.
     * @param request HTTP servlet request to read optional user id from header
     * @param payload NoteRequest payload
     * @return 201 with created note
     */
    @PostMapping
    @Operation(
            summary = "Create note",
            description = "Creates a new note. Optional user scoping via header " + AuthUtil.userHeaderName(),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<NoteResponse> create(
            HttpServletRequest request,
            @Valid @RequestBody NoteRequest payload
    ) {
        if (payload.getTitle() == null || payload.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        String owner = AuthUtil.extractOwnerId(request);
        Note created = service.create(owner, payload);
        return ResponseEntity.created(URI.create("/api/notes/" + created.getId()))
                .body(toResponse(created));
    }

    /**
     * PUBLIC_INTERFACE
     * Get a note by id.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get note by id",
            description = "Fetch a single note. Applies owner scoping if header " + AuthUtil.userHeaderName() + " is provided.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<NoteResponse> getById(
            HttpServletRequest request,
            @Parameter(description = "Note ID") @PathVariable Long id
    ) {
        String owner = AuthUtil.extractOwnerId(request);
        return service.getById(owner, id)
                .map(n -> ResponseEntity.ok(toResponse(n)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * PUBLIC_INTERFACE
     * List all notes (optionally scoped to owner).
     */
    @GetMapping
    @Operation(
            summary = "List notes",
            description = "List notes for current owner if " + AuthUtil.userHeaderName() + " header is specified; otherwise returns all notes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK")
            }
    )
    public List<NoteResponse> list(HttpServletRequest request) {
        String owner = AuthUtil.extractOwnerId(request);
        return service.getAll(owner).stream().map(NotesController::toResponse).collect(Collectors.toList());
    }

    /**
     * PUBLIC_INTERFACE
     * Update a note.
     */
    @PutMapping("/{id}")
    @Operation(
            summary = "Update note",
            description = "Updates a note's title/content. Applies owner scoping if header " + AuthUtil.userHeaderName() + " is provided.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = NoteResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<NoteResponse> update(
            HttpServletRequest request,
            @PathVariable Long id,
            @Valid @RequestBody NoteRequest payload
    ) {
        if (payload.getTitle() != null && payload.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        String owner = AuthUtil.extractOwnerId(request);
        return service.update(owner, id, payload)
                .map(n -> ResponseEntity.ok(toResponse(n)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * PUBLIC_INTERFACE
     * Delete a note by id.
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete note",
            description = "Deletes a note. Applies owner scoping if header " + AuthUtil.userHeaderName() + " is provided.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    public ResponseEntity<Void> delete(
            HttpServletRequest request,
            @Parameter(description = "Note ID") @PathVariable Long id
    ) {
        String owner = AuthUtil.extractOwnerId(request);
        boolean deleted = service.delete(owner, id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
