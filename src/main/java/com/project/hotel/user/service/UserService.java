package com.project.hotel.user.service;

import com.project.hotel.user.domain.User;
import com.project.hotel.user.domain.UserDto;
import com.project.hotel.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public int signUp(UserDto userDto){
        int result = 0;

        String encodedPw = passwordEncoder.encode(userDto.getUserPwd());

        User user = userDto.toEntity();
        user.setUserPw(encodedPw);
        user.setUserNic(user.getUserName());

        try{
            userRepository.save(user);
            result = 1;
        }catch (Exception e){
            e.printStackTrace();
            result = 0;
        }


        return result;
    }
}
