package com.project.hotel.user.repository;

import com.project.hotel.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserId(String userId);
    User findByUserIdAndUserPw(String userId, String userPw);
    User findByUserNameAndEmailAddress(String userName, String emailAddress);
    User findByUserIdAndEmailAddress(String userId, String emailAddress);
}
