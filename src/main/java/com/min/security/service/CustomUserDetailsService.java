package com.min.security.service;

import com.min.security.mapper.SecurityMapper;
import com.min.security.model.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SecurityMapper securityMapper;

    public CustomUserDetailsService(SecurityMapper securityMapper) {
        this.securityMapper = securityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity =  securityMapper.selectByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole())
                .build();
    }
}
