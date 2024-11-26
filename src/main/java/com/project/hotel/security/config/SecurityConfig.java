package com.project.hotel.security.config;

import com.project.hotel.security.config.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final UserDetailService userDetailService;

    public SecurityConfig(CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
                          CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, UserDetailService userDetailService) {
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 토큰 비활성화
                .authorizeRequests()
                .requestMatchers("/", "/sign-up", "/static/**", "/login").permitAll()  // 로그인 및 정적 리소스 허용
                 //.anyRequest().authenticated()  // 그 외 모든 요청은 인증된 사용자만 접근
                .and()
                .formLogin(form -> form
                        .loginPage("/login")  // 로그인 페이지 URL
                        .permitAll()  // 로그인 페이지는 누구나 접근 가능
                        .usernameParameter("userId")
                        .passwordParameter("userPw")
                        .defaultSuccessUrl("/index") // 로그인 성공 시 도착 URL
                        .failureUrl("/login?error=true") // 로그인 실패 시 URL
                        .loginProcessingUrl("/sign-in") // 로그인 폼 액션 URL
                        .successHandler(authenticationSuccessHandler())  // 성공 핸들러 설정
                        .failureHandler(authenticationFailureHandler())  // 실패 핸들러 설정
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()  // 로그아웃 URL도 누구나 접근 가능
                )
                .sessionManagement(auth -> auth // 세션 생성
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl("/")
                );

        return http.build();
    }




    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler(); // 성공 시 처리할 핸들러
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();  // 실패 시 처리할 핸들러
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // 여기서 manager를 통해 userDetailService와 연결
        return authenticationConfiguration.getAuthenticationManager();
    }

}
