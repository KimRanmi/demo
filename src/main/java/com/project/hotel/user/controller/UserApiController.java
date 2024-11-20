package com.project.hotel.user.controller;

import com.project.hotel.user.domain.User;
import com.project.hotel.user.domain.UserDto;
import com.project.hotel.user.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/sign-up")
    @ResponseBody
    public Map<String, String> signUp(@RequestBody UserDto userDto){

        Map<String,String> result = new HashMap<>();
            if (userService.findByUserId(userDto.getUserId()) != null) {
                result.put("res_msg","존재하는 아이디입니다.");
            }else{
                if(userService.signUp(userDto) > 0){
                    result.put("res_code","200");
                    result.put("res_msg","축하합니다.");
                }
        }
        return result;
    }



}
