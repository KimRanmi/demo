package com.project.hotel.review.repository;

import com.project.hotel.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {


}
