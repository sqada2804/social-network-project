package com.example.social_network_project.controllers.Controllers;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.controllers.Interfaces.IPrivateUserController;
import com.example.social_network_project.services.Interface.IUserService;
import org.springframework.http.ResponseEntity;

public class PrivateUserController implements IPrivateUserController {

    private final IUserService userService;

    public PrivateUserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserModel> getUser(String email) {
        return ResponseEntity.ok(userService.getUser(email));
    }
}
