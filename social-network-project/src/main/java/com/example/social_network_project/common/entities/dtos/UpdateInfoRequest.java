package com.example.social_network_project.common.entities.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateInfoRequest {
    @NotNull
    private String name;
    @NotNull
    private String email;
}
