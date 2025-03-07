package com.example.social_network_project.common.entities.dtos;

import com.example.social_network_project.enums.Roles;
import lombok.Data;

@Data
public class UserData {
    private Long userId;
    private String email;
    private String name;
    private Roles role;
}
