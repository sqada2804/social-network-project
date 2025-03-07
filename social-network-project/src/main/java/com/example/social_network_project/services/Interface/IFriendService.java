package com.example.social_network_project.services.Interface;

import com.example.social_network_project.common.entities.UserModel;

import java.util.List;

public interface IFriendService {
    void saveFriend(UserModel userRequest, Long userId);
    public List<?> getFriends(Long userId);
}
