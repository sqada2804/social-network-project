package com.example.social_network_project.repository;

import com.example.social_network_project.common.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUserId(Long userId);
    Optional<UserModel> findByEmail(String email);
}
