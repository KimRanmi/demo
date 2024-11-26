package com.project.hotel.email.controller;

import com.project.hotel.email.service.EmailService;
import com.project.hotel.user.domain.User;
import com.project.hotel.user.domain.UserDto;
import com.project.hotel.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Random;

@Controller
public class emailApiController {

    @Autowired
    private EmailService emailService;
    private UserService userService;

    public emailApiController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/find-pw")
    public ResponseEntity<Object> findPassword(@RequestBody UserDto userDto) {

        // 아이디와 이메일을 통해 유효성 검사 (DB에서 확인)
        User isUserValid = userService.findByUserIdAndemailAddress(userDto);

        if (isUserValid == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("res_code", "400", "res_msg", "아이디 또는 이메일이 일치하지 않습니다."));
        }

        // 임시 비밀번호 생성
        String tempPassword = generateTemporaryPassword();

        try {
            // 임시 비밀번호 이메일로 전송
            emailService.sendTemporaryPassword(userDto.getEmailAddress(), tempPassword,userDto.getUserName());

            // 임시 비밀번호를 DB에 저장 (추후 로그인 시 사용할 수 있도록)
            userService.updateUserPw(userDto.getUserId(), tempPassword);
            return ResponseEntity.ok(Map.of("res_code", "200", "res_msg", "이메일로 임시 비밀번호를 보냈습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("res_code", "500", "res_msg", "이메일 전송에 실패했습니다."));
        }
    }


    // 임시 비밀번호 생성
    private String generateTemporaryPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder tempPassword = new StringBuilder();

        for (int i = 0; i < 8; i++) { // 8자리 임시 비밀번호 생성
            int index = random.nextInt(characters.length());
            tempPassword.append(characters.charAt(index));
        }

        return tempPassword.toString();
    }


}
