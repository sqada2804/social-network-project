package com.example.social_network_project.controllers.Controllers;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.controllers.Interfaces.IPrivateUserController;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import com.example.social_network_project.services.Interface.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrivateUserController implements IPrivateUserController {

    private final IUserService userService;

    public PrivateUserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserModel> getUser(UserRequest userRequest) {
        return ResponseEntity.ok(userService.getUser(userRequest.getEmail()));
    }
}
