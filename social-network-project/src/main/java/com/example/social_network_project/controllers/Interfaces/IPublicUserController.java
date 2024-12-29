package com.example.social_network_project.controllers.Interfaces;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.constants.ApiPathConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.PUBLIC_ROUTE + ApiPathConstants.USER_ROUTE)
public interface IPublicUserController {
    @GetMapping(value = "/getUsers")
    List<UserModel> getUsers();
}
