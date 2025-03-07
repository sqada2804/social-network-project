package com.example.social_network_project.common.entities.dtos;

import com.example.social_network_project.common.entities.UserModel;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FriendsData {
    private Long userId;
    private String name;
}
