package com.min.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    // 로그인 페이지
    @GetMapping("/security/loginForm")
    public String loginForm() {
        return "security/login_form";
    }

    // 회원가입 페이지
    @GetMapping("/security/memberJoinForm")
    public String memberJoinForm() {
        return "security/member_join_form";
    }

    // 권한없음(403) 페이지
    @GetMapping("/security/accessDenied")
    public String accessDenied() {
        return "security/access_denied";
    }

    // USER 권한 접근가능 페이지
    @GetMapping("/user/home")
    public String userHome() {
        return "user/user_home";
    }

    // ADMIN 권한 접근가능 페이지
    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin/admin_home";
    }

}
