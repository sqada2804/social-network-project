package com.example.social_network_project.repository;

import com.example.social_network_project.common.entities.FriendsModel;
import com.example.social_network_project.common.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<FriendsModel, Long> {
    boolean existsByFirstUserAndSecondUser(UserModel first, UserModel second);
    List<FriendsModel> findByFirstUser(UserModel user);
    List<FriendsModel> findBySecondUser(UserModel user);
}
