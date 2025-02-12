package com.example.social_network_project.controllers.Controllers;

import com.example.social_network_project.common.entities.dtos.LoginRequest;
import com.example.social_network_project.common.entities.dtos.TokenResponse;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import com.example.social_network_project.controllers.Interfaces.IAuthController;
import com.example.social_network_project.services.Interface.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> register(UserRequest userRequest) {
        return ResponseEntity.ok(authService.registerUser(userRequest));
    }
}
