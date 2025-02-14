package com.example.social_network_project.controllers.Controllers;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.controllers.Interfaces.IPrivateUserController;
import com.example.social_network_project.services.Interface.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PrivateUserController implements IPrivateUserController {

    private final IUserService userService;

    public PrivateUserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserModel> getUser(@AuthenticationPrincipal UserModel userDetails) {
        if(userDetails == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String email = userDetails.getUsername();
        UserModel user = userService.getUser(email);
        return ResponseEntity.ok(user);
    }
}
