package com.project.hotel.user.controller;

import com.project.hotel.user.domain.UserDto;
import com.project.hotel.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public Map<String, String> signUp(@RequestBody UserDto userDto){

        System.out.println("가니..?");

        Map<String,String> result = new HashMap<>();
        if(userService.signUp(userDto) > 0){
            result.put("res_code","200");
            result.put("res_msg","축하합니다.");
        }
        return result;
    }

}
