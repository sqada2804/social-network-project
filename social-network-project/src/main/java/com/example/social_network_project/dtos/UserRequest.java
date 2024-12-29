package com.example.social_network_project.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NotNull
    private String name;
    @NotNull
    private String email;
}
