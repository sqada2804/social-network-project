package com.example.social_network_project.services.Interface;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.dtos.UserRequest;

import java.util.List;

public interface IUserService {
    void saveUser(UserRequest userRequest);
    UserModel getUser(String email);
    List<UserModel> getAllUsers();
}
