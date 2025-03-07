package com.example.social_network_project.common.entities.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class PostData {
    private Long postId;
    private Long userId;
    private String nameUser;
    private String emailUser;
    private String content;
    private String role;
    private Date createdAt;
}
