package com.example.social_network_project.services.Interface.Service;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.UserToUserDetails;
import com.example.social_network_project.common.entities.dtos.LoginRequest;
import com.example.social_network_project.common.entities.dtos.TokenResponse;
import com.example.social_network_project.common.entities.dtos.UserRequest;
import com.example.social_network_project.enums.Roles;
import com.example.social_network_project.repository.IUserRepository;
import com.example.social_network_project.services.Interface.IAuthService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRequest userRequest;


    public AuthService(IUserRepository userRepository, JWTService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRequest userRequest) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRequest = userRequest;
    }

    @Override
    public TokenResponse registerUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getUserId()))
                .orElseThrow(() -> new RuntimeException("Error register User"));
    }

    @Override
    public TokenResponse loginUser(LoginRequest loginRequest) {
        return Optional.of(loginRequest.getEmail())
                .map(userRepository::findByEmail)
                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword()))
                .map(user -> jwtService.generateToken(user.get().getUserId()))
                .orElseThrow(() -> new RuntimeException("Error login the user"));

    }

    private UserModel mapToEntity(UserRequest userRequest){
        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .role(Roles.USER)
                .build();
    }

    public UserRequest getCurrentUser(){
        String token = getCurrentUserToken();
        String email = getUsernameFromToken(token);
        return getUserByEmail(email);

    }

    public String getUsernameFromToken(String token){
        Claims claims = jwtService.getClaims(token);
        try {
            String email = (String) claims.get("email");
            return email;
        }catch(Exception e){
            throw new RuntimeException("Invalid token or missing email" + e);
        }
    }

    private String getCurrentUserToken(){
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        if(principal instanceof UserToUserDetails){
            return ((UserToUserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    private UserRequest getUserByEmail(String email){
        Optional<UserModel> userOpt = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new RuntimeException("Error finding user by email"));
        UserModel user = userOpt.get();
        return UserRequest.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
