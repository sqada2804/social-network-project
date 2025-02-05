package com.example.social_network_project.controllers.Controllers;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.controllers.Interfaces.IFriendController;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import com.example.social_network_project.services.Interface.IFriendService;
import com.example.social_network_project.services.Interface.Service.AuthService;
import com.example.social_network_project.services.Interface.Service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendController implements IFriendController {

    private final AuthService authService;
    private final IFriendService friendService;

    public FriendController(AuthService authService, FriendService friendService) {
        this.authService = authService;
        this.friendService = friendService;
    }


    @Override
    public ResponseEntity<String> addFriend(String friendId) {
        UserRequest currentUser = authService.getCurrentUser();
        friendService.saveFriend(currentUser, Long.parseLong(friendId));
        return ResponseEntity.ok("Friend added succesfully");
    }

    @Override
    public ResponseEntity<List<UserModel>> getFriends() {
        List<UserModel> myFriends = friendService.getFriends();
        return new ResponseEntity<List<UserModel>>(myFriends, HttpStatus.OK);
    }
}
