package com.project.hotel.user.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class User {

    @Id
    @Column(name="user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @Column(name="user_id")
    private String userId;

    @Column(name="user_pw")
    private String userPw;

    @Column(name="user_nic")
    private String userNic;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_role")
    private String userRole;

    @Column(name="phone_number")
    private Long phoneNumber;

    @Column(name="email_address")
    private String emailAddress;
}
