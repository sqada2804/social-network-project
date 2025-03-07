package com.example.social_network_project.services.Interface.Service;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.dtos.PublicUserData;
import com.example.social_network_project.common.entities.dtos.UserData;
import com.example.social_network_project.repository.IUserRepository;
import com.example.social_network_project.services.Interface.IUserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserData getUser(String email) {
        Optional<UserModel> userOpt = userRepository.findByEmail(email);
        UserModel user = userOpt.get();

        UserData userData = new UserData();
        userData.setUserId(user.getUserId());
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setRole(user.getRole());
        return userData;
    }

    @Override
    public List<PublicUserData> getAllUsers() {
        List<UserModel> users = userRepository.findAll();
        return users.stream()
                .map(user ->{
                    PublicUserData publicData = new PublicUserData();
                    publicData.setUserId(user.getUserId());
                    publicData.setName(user.getName());
                    publicData.setEmail(user.getEmail());
                    return publicData;
                }).collect(Collectors.toList());
    }
}
