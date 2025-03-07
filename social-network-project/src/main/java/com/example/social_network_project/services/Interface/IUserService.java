package com.example.social_network_project.services.Interface;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.dtos.PublicUserData;
import com.example.social_network_project.common.entities.dtos.UserData;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IUserService {
    UserData getUser(@PathVariable String email);
    List<PublicUserData> getAllUsers();
}
