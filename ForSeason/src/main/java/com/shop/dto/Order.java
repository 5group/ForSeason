package com.shop.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private int order_no;
    private int user_no;
    private int order_tot;
    private String order_pay;
    private String ship_addr;
    private String ship_cust;
    private String ship_stat;
    private Date order_udate;
}