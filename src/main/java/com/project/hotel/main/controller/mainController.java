package com.project.hotel.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("/ourStory")
    public String ourStory(){
        return "our-story";
    }

    @GetMapping("/contact")
    public String conTact(){
        return "contact";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

    @GetMapping("/gallery")
    public String gallery(){
        return "gallery";
    }

    @GetMapping("/event")
    public String event(){
        return "events";
    }
}
