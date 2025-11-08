package com.assessment.common.security.service;

import com.assessment.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String username;


    @JsonIgnore
    private String password;


    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetails build(User user) {
//        List<SimpleGrantedAuthority> authorities = List.of(user.getRole()).stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .toList();
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + "ADMIN")
        );
        return new UserDetailsImpl(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                authorities
        );
    }

}
