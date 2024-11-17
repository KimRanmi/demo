package com.project.hotel.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))  // CSRF 토큰 설정
                .authorizeRequests()
                .requestMatchers("/","/sign-up").permitAll()
                /*.anyRequest().authenticated()  // 그 외 요청은 인증된 사용자만 접근 가능*/
                .and()
                .formLogin(form -> form
                        .loginPage("/login")  // 로그인 페이지 URL
                        .loginProcessingUrl("/login")  // 로그인 요청을 처리할 URL
                        .permitAll()  // 로그인 페이지는 누구나 접근 가능
                )
                .logout(logout -> logout
                        .permitAll()  // 로그아웃 페이지도 누구나 접근 가능
                );

        return http.build();  // SecurityFilterChain을 반환
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
