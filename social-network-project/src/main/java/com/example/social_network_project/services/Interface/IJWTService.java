package com.example.social_network_project.services.Interface;

import com.example.social_network_project.common.entities.dtos.TokenResponse;
import io.jsonwebtoken.Claims;

public interface IJWTService {
    TokenResponse generateToken(Long userId);
    Claims getClaims(String token);
    boolean isExpired(String token);
    Integer extractedUserId(String token);
}
