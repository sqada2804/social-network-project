package com.example.social_network_project.controllers.Interfaces;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.FRIEND_ROUTE)
public interface IFriendController {
    @PostMapping(value = "/addFriend/{friendId}")
    ResponseEntity<String> addFriend(UserModel userRequest, @PathVariable("friendId") String friendId);

    @GetMapping(value = "/listFriends")
    ResponseEntity<?> getFriends(UserModel user);
}
