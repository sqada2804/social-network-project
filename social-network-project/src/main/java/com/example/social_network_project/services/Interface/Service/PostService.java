package com.example.social_network_project.services.Interface.Service;

import com.example.social_network_project.common.entities.PostModel;
import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.dtos.PostRequest;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import com.example.social_network_project.repository.IPostRepository;
import com.example.social_network_project.repository.IUserRepository;
import com.example.social_network_project.services.Interface.IAuthService;
import com.example.social_network_project.services.Interface.IPostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostService implements IPostService {

    private final IPostRepository postRepository;
    private final IUserRepository userRepository;
    private final IAuthService authService;

    public PostService(IPostRepository postRepository, IUserRepository userRepository, IAuthService authService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }


    @Override
    public PostModel savePost(UserRequest userRequest, String content) {
        PostModel postModel = new PostModel();
        Optional<UserModel> userOpt = userRepository.findUserByEmail(userRequest.getEmail());
        UserModel userModel = userOpt.get();
        postModel.setUserModel(userModel);
        postModel.setContent(content);
        return postRepository.save(postModel);
    }

    @Override
    public List<PostRequest> getPostOfUser(Long userId) {
        List<PostModel> postList = postRepository.findPostByUserOrderById(userRepository.findUserById(userId));
        List<PostRequest> postRequestList = new ArrayList<>();
        for(PostModel postModel:  postList){
            postRequestList.add(mapToDTO(postModel));
        }
        return postRequestList;
    }

    @Override
    public List<PostModel> getAllPost() {
        return postRepository.findAllByOrderIdDesc();
    }

    private PostRequest mapToDTO(PostModel postRequest){
        return PostRequest.builder()
                .content(postRequest.getContent())
                .build();
    }
}
