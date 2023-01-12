package com.shop.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {

    private int rev_no;
    private int item_no;
    private int user_no;
    private String rev_title;
    private String rev_content;
    private int rev_score;
    private Date rev_udate;
    private Date rev_rdate;
}
