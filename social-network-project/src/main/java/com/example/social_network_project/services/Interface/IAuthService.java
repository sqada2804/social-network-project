package com.example.social_network_project.services.Interface;

import com.example.social_network_project.common.entities.dtos.LoginRequest;
import com.example.social_network_project.common.entities.dtos.TokenResponse;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthService {
    TokenResponse registerUser(@RequestBody UserRequest userRequest);
    TokenResponse loginUser(@RequestBody LoginRequest loginRequest);
}
