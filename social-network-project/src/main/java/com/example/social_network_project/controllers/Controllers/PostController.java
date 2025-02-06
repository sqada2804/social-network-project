package com.example.social_network_project.controllers.Controllers;

import com.example.social_network_project.common.entities.PostModel;
import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.dtos.PostRequest;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import com.example.social_network_project.controllers.Interfaces.IPostController;
import com.example.social_network_project.services.Interface.IAuthService;
import com.example.social_network_project.services.Interface.IPostService;
import com.example.social_network_project.services.Interface.IUserService;
import com.example.social_network_project.services.Interface.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController implements IPostController {

    private final AuthService authService;
    private final IPostService postService;
    private final IUserService userService;

    public PostController(AuthService authService, IPostService postService, IUserService userService) {
        this.authService = authService;
        this.postService = postService;
        this.userService = userService;
    }


    @Override
    public ResponseEntity<?> addPost(PostModel postModel) throws NullPointerException {
        UserRequest user = authService.getCurrentUser();
        PostModel savedPost = postService.savePost(user, postModel.getContent());
        return ResponseEntity.ok(savedPost);
    }

    @Override
    public ResponseEntity<?> myPosts() throws NullPointerException {
        UserModel user = userService.getUser(authService.getCurrentUser().getEmail());
        List<?> postList = postService.getPostOfUser(user.getUserId());
        return ResponseEntity.ok(postList);
    }

    @Override
    public ResponseEntity<List<PostModel>> getAllPosts() {
        List<PostModel> postList = postService.getAllPost();
        return ResponseEntity.ok(postList);
    }

    @Override
    public ResponseEntity<?> getPostOfUser(Long userId) {
        List<?> postList = postService.getPostOfUser(userId);
        return ResponseEntity.ok(postList);
    }
}
