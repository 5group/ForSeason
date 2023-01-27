package com.admin.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cart {
    private int cart_no;
    private int stock_no;
    private int user_no;
    private int cart_cnt;
    private Date cart_date;
}
