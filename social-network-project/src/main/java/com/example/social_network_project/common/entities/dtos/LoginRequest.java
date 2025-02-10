package com.example.social_network_project.common.entities.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
public class LoginRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
