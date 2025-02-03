package com.min.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 암호화 모듈 선언
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 비활성화
        http.csrf(AbstractHttpConfigurer::disable);

        // 인증 및 인가 설정(인가만 설정되어있음)
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
        )
                // 로그인 설정
                .formLogin(formLogin -> formLogin
                        .loginPage("/security/loginForm")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/user/home")
                        .failureUrl("/loginForm?error=true")
                        .failureHandler((request, response, exception) -> {
                            System.out.println(exception.getMessage());
                            request.getRequestDispatcher("/loginForm?error=" + exception.getMessage()).forward(request, response);
                        })
                )
                // 로그아웃 설정
                .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/security/loginForm")
                                .deleteCookies("JSESSIONID", "remember-me")
                                .invalidateHttpSession(true)
                );

        http.exceptionHandling(conf -> conf
                // 인증되지 않은 사용자가 접근할 때 로그인 페이지로 리디렉션
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/security/loginForm");
                })
                // 권한이 없는 사용자가 접근할 때 접근 거부 페이지로 리디렉션
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/security/accessDenied");
                })
        );

        return http.build();
    }

}
