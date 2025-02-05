package com.example.social_network_project.services.Interface.Service;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.dtos.UpdateInfoRequest;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import com.example.social_network_project.repository.IUserRepository;
import com.example.social_network_project.services.Interface.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel getUser(String email) {
        return Optional.ofNullable(email)
                .flatMap(userRepository::findByEmail)
                .orElseThrow(() -> new RuntimeException("Error finding the user by email"));
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAllUsers();
    }
}
