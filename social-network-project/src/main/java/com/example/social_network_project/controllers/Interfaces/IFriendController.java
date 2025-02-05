package com.example.social_network_project.controllers.Interfaces;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.constants.ApiPathConstants;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.FRIEND_ROUTE)
public interface IFriendController {
    @GetMapping(value = "/addFriend")
    ResponseEntity<String> addFriend(@RequestParam("friendId") String friendId);

    @GetMapping(value = "/listFriends")
    ResponseEntity<List<UserModel>> getFriends();
}
