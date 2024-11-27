package com.project.hotel.review.controller;

import com.project.hotel.review.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewViewController {

    private final ReviewService reviewService;

    public ReviewViewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }

    @GetMapping("/review")
    public String review(){
        return "/review";
    }


}
