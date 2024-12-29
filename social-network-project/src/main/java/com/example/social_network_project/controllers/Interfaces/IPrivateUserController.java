package com.example.social_network_project.controllers.Interfaces;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.PRIVATE_ROUTE + ApiPathConstants.USER_ROUTE)
public interface IPrivateUserController {
    @GetMapping(value = "/getUser")
    ResponseEntity<UserModel> getUser(String email);
}
