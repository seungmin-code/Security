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
        // 아이디 존재여부 조회
        UserEntity userEntity =  securityMapper.selectByUsername(username);

        // 일치하는 아이디가 없을 시 예외처리
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        // 회원정보 반환
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole())
                .build();
    }
}
