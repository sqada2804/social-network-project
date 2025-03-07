package com.example.social_network_project.controllers.Controllers;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.controllers.Interfaces.IFriendController;
import com.example.social_network_project.services.Interface.IFriendService;
import com.example.social_network_project.services.Interface.IUserService;
import com.example.social_network_project.services.Interface.Service.AuthService;
import com.example.social_network_project.services.Interface.Service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendController implements IFriendController {

    private final AuthService authService;
    private final IFriendService friendService;
    private final IUserService userService;

    public FriendController(AuthService authService, FriendService friendService, IUserService userService) {
        this.authService = authService;
        this.friendService = friendService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> addFriend(@AuthenticationPrincipal UserModel user, String friendId) {
        friendService.saveFriend(user, Long.parseLong(friendId));
        return ResponseEntity.ok("Friend added succesfully");
    }

    @Override
    public ResponseEntity<?> getFriends(@AuthenticationPrincipal UserModel user) {
        List<?> myFriends = friendService.getFriends(user.getUserId());
        return ResponseEntity.ok(myFriends);
    }
}
