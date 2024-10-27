package com.example.amazon_agency_task.security;


public record AuthRequest(
        String name,
        String password
) {
}
