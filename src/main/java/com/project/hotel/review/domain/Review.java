package com.project.hotel.review.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class Review {

    @Id
    @Column(name="re_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reNo;

    @Column(name="re_title")
    private String reTitle;

    @Column(name="re_content")
    private String reContent;

    @Column(name="re_status")
    private String reStatus;

    @Column(name="re_reg_date")
    @CreationTimestamp
    private LocalDateTime reRegDate;

    @Column(name="re_mod_date")
    @UpdateTimestamp
    private LocalDateTime reModDate;

    @Column(name="re_file")
    private String reFile;

    @Column(name="re_writer")
    private String reWriter;








}
