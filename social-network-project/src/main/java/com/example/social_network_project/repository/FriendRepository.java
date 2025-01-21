package com.example.social_network_project.repository;

import com.example.social_network_project.common.entities.FriendsModel;
import com.example.social_network_project.common.entities.UserModel;
import org.apache.catalina.User;

import java.util.List;

public interface FriendRepository {
    boolean existsByFirstUserAndSecondUser(UserModel first, UserModel second);
    List<FriendsModel> findByFirstUser(UserModel user);
    List<FriendsModel> findBySecondUser(UserModel user);

}
