package com.shop.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Coupon {

    private int cou_no;
    private String cou_name;
    private String cou_info;
    private Date cou_date;
    private int user_no;
}
