package com.project.hotel;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

       @GetMapping("/")
        public String home() {
            return "forms/index";
        }

}
