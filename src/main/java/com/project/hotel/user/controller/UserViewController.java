package com.project.hotel.user.controller;

import com.project.hotel.user.domain.User;
import com.project.hotel.user.domain.UserDto;
import com.project.hotel.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserViewController {

    private final UserService userService;

    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/signUp")
    public String signUP(){
        return "/sign-up";
    }

}
