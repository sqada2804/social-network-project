package com.example.social_network_project.services.Interface.Service;

import com.example.social_network_project.common.entities.UserModel;
import com.example.social_network_project.common.entities.UserToUserDetails;
import com.example.social_network_project.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    public UserDetailsImpl(IUserRepository userRepository, UserToUserDetails userDetails) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .map(UserToUserDetails::new)
                .orElseThrow(() -> new RuntimeException("UserDetailsService user not found"));
    }

}
