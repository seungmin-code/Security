package com.min.security.service;

import com.min.security.mapper.SecurityMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecurityService {

    private final SecurityMapper securityMapper;
    private final PasswordEncoder passwordEncoder;

    public SecurityService(SecurityMapper securityMapper, PasswordEncoder passwordEncoder) {
        this.securityMapper = securityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public void memberJoin(Map<String, Object> params) {
        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(params.get("password").toString());
        params.put("password", encryptedPassword);

        securityMapper.memberJoin(params);
    }
}
