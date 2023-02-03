package com.shop.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private int od_no;
    private int order_no;
    private int stock_no; //
    private int od_cnt;
    private int od_price;
    private int od_dicnt; //

    // 외부에서 가져올 데이터들
    private Date order_udate;
    private String order_stat;
    private int order_tot;
    private String item_name;
    private String color_name;
    private String size_name;
    private String order_cp;

    public OrderDetail(int od_no, int order_no, int stock_no, int od_cnt, int od_price, int od_dicnt) {
        this.od_no = od_no;
        this.order_no = order_no;
        this.stock_no = stock_no;
        this.od_cnt = od_cnt;
        this.od_price = od_price;
        this.od_dicnt = od_dicnt;
    }

    public OrderDetail(int od_no, int order_no, int od_cnt, int od_price, Date order_udate, String order_stat, int order_tot, String item_name, String color_name, String size_name, String order_cp) {
        this.od_no = od_no;
        this.order_no = order_no;
        this.od_cnt = od_cnt;
        this.od_price = od_price;
        this.order_udate = order_udate;
        this.order_stat = order_stat;
        this.order_tot = order_tot;
        this.item_name = item_name;
        this.color_name = color_name;
        this.size_name = size_name;
        this.order_cp = order_cp;
    }
}
