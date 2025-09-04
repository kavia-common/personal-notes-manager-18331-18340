package com.example.notesappbackend.web;

import com.example.notesappbackend.security.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * PUBLIC_INTERFACE
 * Optional demo authentication controller.
 * This is a placeholder to illustrate how a client might set an owner id.
 * Real authentication should be implemented with Spring Security and a proper identity provider.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth (Demo)", description = "Optional demo endpoints for simple header-based 'authentication'")
public class AuthController {

    /**
     * PUBLIC_INTERFACE
     * Returns the current user id as seen via request header X-User-Id.
     */
    @GetMapping("/me")
    @Operation(summary = "Current user info (demo)", description = "Returns the current user id inferred from header " + "X-User-Id")
    public Map<String, Object> me(@RequestHeader(value = "X-User-Id", required = false) String userId) {
        return Map.of(
                "userId", userId,
                "header", AuthUtil.userHeaderName(),
                "note", "Set header " + AuthUtil.userHeaderName() + " to scope notes by user."
        );
    }

    /**
     * PUBLIC_INTERFACE
     * Demo login endpoint that simply echoes the provided user id to be used in X-User-Id header.
     */
    @PostMapping("/login")
    @Operation(summary = "Login (demo)", description = "Returns the provided username to use as " + AuthUtil.userHeaderName() + " header.")
    public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username) {
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "username is required"));
        }
        return ResponseEntity.ok(Map.of(
                "userId", username.trim(),
                "useHeader", AuthUtil.userHeaderName(),
                "instructions", "Send future requests with header " + AuthUtil.userHeaderName() + ": " + username.trim()
        ));
    }
}
