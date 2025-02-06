package com.example.social_network_project.services.Interface;

import com.example.social_network_project.common.entities.PostModel;
import com.example.social_network_project.common.entities.dtos.PostRequest;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {
    public PostModel savePost(UserRequest userRequest, String content);
    public List<PostRequest> getPostOfUser(Long userId);
    public List<PostModel> getAllPost();
}
