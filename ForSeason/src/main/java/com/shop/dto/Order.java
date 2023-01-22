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
    private String order_stat;
    private int order_cp;
    private Date order_udate;

    public Order(int user_no, int order_tot, String order_pay, String ship_addr, String ship_cust, String ship_stat, String order_stat, int order_cp) {
        this.user_no = user_no;
        this.order_tot = order_tot;
        this.order_pay = order_pay;
        this.ship_addr = ship_addr;
        this.ship_cust = ship_cust;
        this.ship_stat = ship_stat;
        this.order_stat = order_stat;
        this.order_cp = order_cp;
    }
}