package com.example.social_network_project.services.Interface.Service;

import com.example.social_network_project.common.entities.PostModel;
import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.dtos.PostData;
import com.example.social_network_project.common.entities.dtos.PostRequest;
import com.example.social_network_project.repository.IPostRepository;
import com.example.social_network_project.repository.IUserRepository;
import com.example.social_network_project.services.Interface.IAuthService;
import com.example.social_network_project.services.Interface.IPostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
    public PostData savePost(UserModel userRequest, String content) {
        PostModel postModel = new PostModel();
        Optional<UserModel> userOpt = userRepository.findByEmail(userRequest.getEmail());
        UserModel userModel = userOpt.get();
        postModel.setUserModel(userModel);
        postModel.setContent(content);
        postRepository.save(postModel);

        PostData postData = new PostData();
        postData.setPostId(postModel.getPostId());
        postData.setUserId(userModel.getUserId());
        postData.setNameUser(userModel.getName());
        postData.setEmailUser(userModel.getEmail());
        postData.setContent(postModel.getContent());
        postData.setRole(userModel.getRole().name());
        postData.setCreatedAt(postModel.getCreatedAt());

        return postData;
    }

    @Override
    public List<?> getPostOfUser(Long userId) {
        List<PostModel> postList = postRepository.findPostByUserModel(userRepository.findByUserId(userId));
        List<PostRequest> postRequestList = new ArrayList<>();
        for(PostModel postModel:  postList){
            postRequestList.add(mapToDTO(postModel));
        }
        return postRequestList;
    }

    @Override
    public List<PostModel> getAllPost() {
        return postRepository.findAll();
    }

    private PostRequest mapToDTO(PostModel postRequest){
        return PostRequest.builder()
                .content(postRequest.getContent())
                .build();
    }
}
