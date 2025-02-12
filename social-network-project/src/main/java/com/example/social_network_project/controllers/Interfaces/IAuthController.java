package com.example.social_network_project.controllers.Interfaces;

import com.example.social_network_project.common.entities.constants.ApiPathConstants;
import com.example.social_network_project.common.entities.dtos.LoginRequest;
import com.example.social_network_project.common.entities.dtos.TokenResponse;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.USER_ROUTE)
public interface IAuthController {
    @PostMapping("/login")
    ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest loginRequest);

    @PostMapping("/register")
    ResponseEntity<TokenResponse> register(@RequestBody @Valid UserRequest userRequest);
}
