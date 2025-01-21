package com.example.social_network_project.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;

}
