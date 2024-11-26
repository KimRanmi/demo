package com.project.hotel.review.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ReviewDto {

    private Long re_no;
    private String re_title;
    private String re_content;
    private String re_status;
    private LocalDateTime re_reg_date;
    private LocalDateTime re_mod_date;
    private String re_file;
    private String re_writer;

    public Review toEntity(){
        return Review.builder()
                .reNo(re_no)
                .reTitle(re_title)
                .reContent(re_content)
                .reStatus(re_status)
                .reRegDate(re_reg_date)
                .reModDate(re_mod_date)
                .reFile(re_file)
                .reWriter(re_writer)
                .build();


    }

    public ReviewDto toDto(Review review){
        return ReviewDto.builder()
                .re_no(review.getReNo())
                .re_title(review.getReTitle())
                .re_content(review.getReContent())
                .re_status(review.getReStatus())
                .re_reg_date(review.getReRegDate())
                .re_mod_date(review.getReModDate())
                .re_file(review.getReFile())
                .re_writer(review.getReWriter())
                .build();
    }

}
