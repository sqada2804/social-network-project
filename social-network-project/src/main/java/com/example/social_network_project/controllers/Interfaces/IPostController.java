package com.example.social_network_project.controllers.Interfaces;

import com.example.social_network_project.common.entities.PostModel;
import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.constants.ApiPathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.POST_ROUTE)
public interface IPostController {
    @PostMapping("/addpost")
    public ResponseEntity<?> addPost(@RequestBody PostModel postModel, UserModel user) throws NullPointerException;

    @GetMapping("/mypost")
    public ResponseEntity<?> myPosts(UserModel user) throws NullPointerException;

    @GetMapping("/posts")
    public ResponseEntity<List<PostModel>> getAllPosts();

    @GetMapping("posts/{userId}")
    public ResponseEntity<?> getPostOfUser(@PathVariable Long userId);

}
