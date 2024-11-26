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
        System.out.println(userDto);
        String encodedPw = passwordEncoder.encode(userDto.getUserPw());

        User user = userDto.toEntity();
        user.setUserPw(encodedPw);
        user.setUserNic(user.getUserName());
        user.setUserRole("user");

        try{
            userRepository.save(user);
            result = 1;
        }catch (Exception e){
            e.printStackTrace();
            result = 0;
        }

        return result;
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public Boolean findByUser(UserDto userDto){
        User user = userRepository.findByUserId(userDto.getUserId());
        if(user != null){
            System.out.println("결과1: "+user.getUserPw()+","+userDto.getUserPw());
            return passwordEncoder.matches(userDto.getUserPw(), user.getUserPw());
        }else{
            return false;
        }
    }

    public User findByUserNameAndemailAddress(UserDto userDto){
        return userRepository.findByUserNameAndEmailAddress(userDto.getUserName(),userDto.getEmailAddress());
    }

    public User findByUserIdAndemailAddress(UserDto userDto){
        return userRepository.findByUserIdAndEmailAddress(userDto.getUserId(),userDto.getEmailAddress());
    }

    public User updateUserPw(String userId, String tempPassword){
        User user = userRepository.findByUserId(userId);
        String encodedPw = passwordEncoder.encode(tempPassword);

        user.setUserPw(encodedPw);
        return userRepository.save(user);
    }
}
