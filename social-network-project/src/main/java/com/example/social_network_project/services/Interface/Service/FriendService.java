package com.example.social_network_project.services.Interface.Service;

import com.example.social_network_project.common.entities.FriendsModel;
import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import com.example.social_network_project.repository.FriendRepository;
import com.example.social_network_project.repository.IUserRepository;
import com.example.social_network_project.services.Interface.IFriendService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService implements IFriendService {

    private final FriendRepository friendRepository;
    private final IUserRepository userRepository;
    private final AuthService authService;

    public FriendService(FriendRepository friendRepository, IUserRepository userRepository, AuthService authService) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }


    @Override
    public void saveFriend(UserRequest userDTO1, Long userId) {
        Optional<UserModel> userOpt = Optional.of(userRepository.getReferenceById(userId));
        UserRequest userDTO2 = userOpt.map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Error to saving the friend"));

        FriendsModel friendsModel = createFriend(userDTO1, userDTO2);

        if(!(friendRepository.existsByFirstUserAndSecondUser(friendsModel.getFirstUser(), friendsModel.getSecondUser()))){
            friendsModel.setCreatedDate(new Date());
            friendRepository.save(friendsModel);
        }
    }

    private FriendsModel createFriend(UserRequest userDTO1, UserRequest userDTO2) {
        Optional<UserModel> firstUserOpt = Optional.ofNullable(userRepository.findByEmail(userDTO1.getEmail())
                .orElseThrow(() -> new RuntimeException("Error finding first user")));
        Optional<UserModel> secondUserOpt = Optional.ofNullable(userRepository.findUserByEmail(userDTO2.getEmail()))
                .orElseThrow(() -> new RuntimeException("Error finding second user"));

        FriendsModel friend = new FriendsModel();
        UserModel user1 = firstUserOpt.get();
        UserModel user2 = secondUserOpt.get();

        UserModel firstUser = user1;
        UserModel secondUser = user2;

        if (user1.getUserId() > user2.getUserId()) {
            firstUser = user2;
            secondUser = user1;
        } else {
            firstUser = user1;
            secondUser = user2;
        }
        FriendsModel friendsModel = new FriendsModel();
        friendsModel.setFirstUser(firstUser);
        friendsModel.setSecondUser(secondUser);
        return friendsModel;
    }

    private UserRequest mapToDTO(UserModel userModel) {
        return UserRequest.builder()
                .email(userModel.getEmail())
                .name(userModel.getName())
                .build();
    }

    private UserModel mapToEntity(UserRequest userRequest){
        return UserModel.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

    @Override
    public List<UserModel> getFriends() {
        UserRequest currentUserDTO = authService.getCurrentUser();
        Optional<UserModel> currentUserOpt = Optional.ofNullable(userRepository.findUserByEmail(currentUserDTO.getEmail()))
                .orElseThrow(() -> new RuntimeException("Error finding user by email"));

        UserModel currentUser = currentUserOpt.get();
        List<UserModel> friendsUser = new ArrayList<>();
        addFriends(friendRepository.findByFirstUser(currentUser), friendsUser, true);
        addFriends(friendRepository.findBySecondUser(currentUser), friendsUser, false);
        return friendsUser;
    }

    private void addFriends(List<FriendsModel> friends, List<UserModel> friendsUser, boolean isFirstUser){
        friends.forEach(friend -> {
            UserModel user = isFirstUser ? friend.getSecondUser() : friend.getFirstUser();
            friendsUser.add(Optional.ofNullable(userRepository.findUserById(user.getUserId())).orElseThrow(() -> new RuntimeException("Error getting user By Id")));
        });
    }
}
