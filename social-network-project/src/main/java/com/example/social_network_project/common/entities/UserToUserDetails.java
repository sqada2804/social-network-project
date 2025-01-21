package com.example.social_network_project.common.entities;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class UserToUserDetails extends UserModel {
    private final UserModel userModel;

    public UserToUserDetails(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userModel.getAuthorities();
    }

    @Override
    public String getUsername() {
        return userModel.getEmail();
    }

    @Override public String getPassword() { return userModel.getPassword(); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
