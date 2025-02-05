package com.example.social_network_project.controllers.Interfaces;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.constants.ApiPathConstants;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.PRIVATE_ROUTE + ApiPathConstants.USER_ROUTE)
@CrossOrigin(origins = "*", allowedHeaders = "*")

public interface IPrivateUserController {
    @GetMapping(value = "/getUser")
    ResponseEntity<UserModel> getUser(@AuthenticationPrincipal UserRequest userRequest);
}
