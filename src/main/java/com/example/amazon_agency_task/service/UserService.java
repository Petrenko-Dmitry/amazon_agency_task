package com.example.amazon_agency_task.service;

import com.example.amazon_agency_task.security.AuthRequest;
import com.example.amazon_agency_task.security.AuthResponse;

public interface UserService {
    void register(AuthRequest user);
    AuthResponse login(AuthRequest user);
}
