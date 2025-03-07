package com.example.social_network_project.controllers.Interfaces;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.constants.ApiPathConstants;
import com.example.social_network_project.common.entities.dtos.UserData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.PRIVATE_ROUTE + ApiPathConstants.USER_ROUTE)
@CrossOrigin(origins = "*", allowedHeaders = "*")

public interface IPrivateUserController {
    @GetMapping(value = "/getUser")
    ResponseEntity<UserData> getUser(UserModel userRequest);
}
