package com.example.social_network_project.repository;

import com.example.social_network_project.common.entities.PostModel;
import com.example.social_network_project.common.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<PostModel, Long> {
    List<PostModel> findPostByUserModel(UserModel userModel);
}
