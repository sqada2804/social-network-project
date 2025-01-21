package com.example.social_network_project.common.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private String role;
    private Collection<? extends GrantedAuthority> authorities;

    public UserModel(String name, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override public String getPassword() { return password; }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

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
