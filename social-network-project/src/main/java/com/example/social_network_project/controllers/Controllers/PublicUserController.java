package com.example.social_network_project.controllers.Controllers;

import com.example.social_network_project.common.entities.dtos.PublicUserData;
import com.example.social_network_project.controllers.Interfaces.IPublicUserController;
import com.example.social_network_project.services.Interface.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublicUserController implements IPublicUserController {

    private final IUserService userService;

    public PublicUserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<PublicUserData>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}

