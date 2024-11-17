package com.project.hotel.user.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class UserDto {

    private Long userNo;
    private String userId;
    private String userPwd;
    private String userName;
    private String userNic;
    private String userRole;
    private Long phoneNumber;
    private String emailAddress;

    public User toEntity(){
        return User.builder()
                .userNo(userNo)
                .userId(userId)
                .userPw(userPwd)
                .userRole(userRole)
                .userNic(userNic)
                .emailAddress(emailAddress)
                .phoneNumber(phoneNumber)
                .userName(userName)
                .build();
    }

    public static UserDto toDto(User user){
        return UserDto.builder()
                .userNo(user.getUserNo())
                .userId(user.getUserId())
                .userPwd(user.getUserPw())
                .userNic(user.getUserNic())
                .userRole(user.getUserRole())
                .phoneNumber(user.getPhoneNumber())
                .emailAddress(user.getEmailAddress())
                .userName(user.getUserName())
                .build();
    }

}
