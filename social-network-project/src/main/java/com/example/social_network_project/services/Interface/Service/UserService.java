package com.example.social_network_project.services.Interface.Service;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.dtos.UserRequest;
import com.example.social_network_project.repository.IUserRepository;
import com.example.social_network_project.services.Interface.IUserService;
import org.apache.catalina.User;
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
    public void saveUser(UserRequest userRequest) {
        Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .orElseThrow(() -> new RuntimeException("Error saving the user"));
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

    private UserModel mapToEntity(UserRequest userRequest){
        return UserModel.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .build();
    }
}
