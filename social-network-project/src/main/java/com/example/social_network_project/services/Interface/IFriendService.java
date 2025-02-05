package com.example.social_network_project.services.Interface;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.dtos.UserRequest;

import java.util.List;

public interface IFriendService {
    void saveFriend(UserRequest userRequest, Long userId);
    List<UserModel> getFriends();

}
