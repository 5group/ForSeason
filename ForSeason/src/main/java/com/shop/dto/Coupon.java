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
    private int cou_price;
    private int user_no;
    private int cou_status;
    private Date cou_rdate;
    private Date cou_udate;

    public Coupon(String cou_name, int cou_price, int user_no) {
        this.cou_name = cou_name;
        this.cou_price = cou_price;
        this.user_no = user_no;
    }
}
