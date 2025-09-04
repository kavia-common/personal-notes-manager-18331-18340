package com.example.notesappbackend.security;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Helper to extract a simple owner identifier from request headers.
 * This is NOT secure authentication; it's an optional scoping helper.
 * Uses X-User-Id header if present.
 */
public final class AuthUtil {

    private static final String USER_HEADER = "X-User-Id";

    private AuthUtil() {}

    /**
     * PUBLIC_INTERFACE
     * Extract owner/user id from request header "X-User-Id".
     * Returns null if not present or blank.
     */
    public static String extractOwnerId(HttpServletRequest request) {
        String userId = request.getHeader(USER_HEADER);
        if (!StringUtils.hasText(userId)) {
            return null;
        }
        return userId.trim();
    }

    /**
     * PUBLIC_INTERFACE
     * Name of the header used for optional user scoping.
     */
    public static String userHeaderName() {
        return USER_HEADER;
    }
}
