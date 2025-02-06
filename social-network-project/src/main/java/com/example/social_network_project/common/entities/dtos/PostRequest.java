package com.example.social_network_project.common.entities.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostRequest {
    private String content;
}
