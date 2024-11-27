package com.project.hotel.review.controller;

import com.project.hotel.review.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReviewApiController {

    private final ReviewService reviewService;

    public ReviewApiController(ReviewService reviewService){
        this.reviewService=reviewService;
    }

//    @PostMapping("/create")
//    @ResponseBody
//    public

}
