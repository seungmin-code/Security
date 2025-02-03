package com.min.security.controller;

import com.min.security.service.SecurityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SecurityRestController {

    private final SecurityService securityService;

    public SecurityRestController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/memberJoin")
    public void memberJoin(@RequestParam Map<String, Object> params) {
        securityService.memberJoin(params);
    }
}
