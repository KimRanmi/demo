package com.project.hotel.security.config.service;

import com.project.hotel.user.domain.User;
import com.project.hotel.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

    @Service
    public class UserDetailService implements UserDetailsService {

        private final UserRepository userRepository;

        public UserDetailService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
            System.out.println("왓니..?");
            User user = userRepository.findByUserId(userId);  // DB에서 사용자 조회
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            // Spring Security의 User 객체로 변환
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserId())
                    .password(user.getUserPw())  // 암호화된 비밀번호
                    .roles(user.getUserRole())  // 권한
                    .build();
        }
}
