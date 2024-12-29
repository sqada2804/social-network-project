package com.example.social_network_project.controllers.Controllers;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.controllers.Interfaces.IPublicUserController;
import com.example.social_network_project.services.Interface.IUserService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PublicUserController implements IPublicUserController {

    private final IUserService userService;

    public PublicUserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserModel> getUsers() {
        return userService.getAllUsers();
    }

}
