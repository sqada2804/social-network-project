package com.example.social_network_project.services.Interface;

import com.example.social_network_project.common.entities.UserModel;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

public interface IUserService {
    UserModel getUser(@PathVariable String email);
    List<UserModel> getAllUsers();
}
