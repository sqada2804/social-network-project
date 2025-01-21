package com.example.social_network_project.services.Interface;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.dtos.LoginRequest;
import com.example.social_network_project.dtos.TokenResponse;
import com.example.social_network_project.dtos.UserRequest;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthService {
    TokenResponse registerUser(@RequestBody UserRequest userRequest);
    TokenResponse loginUser(@RequestBody LoginRequest loginRequest);
}
