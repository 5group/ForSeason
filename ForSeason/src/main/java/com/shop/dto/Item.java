package com.shop.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    private int item_no;
    private int cate_no;
    private String item_name;
    private int item_price;
    private int item_discnt;
    private String item_info;
    private int item_hit;
    private Date item_udate;

    private int rcnt;  //설재경 추가
    private double revavg_score; //설재경 추가
    
    private int item_order_total; //아이템이 주문된 갯수

    
    //private int cate_no2;

    public Item(int cate_no, String item_name, int item_price, int item_discnt, String item_info, int item_hit) {
        this.cate_no = cate_no;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_discnt = item_discnt;
        this.item_info = item_info;
        this.item_hit = item_hit;
    }
    
//    public Item(int item_no, String item_name, int item_price, int item_discnt, String item_info, int item_hit, Date item_udate, int cate_no, int cate_no2) {
//    	this.item_no = item_no;
//        this.item_name = item_name;
//        this.item_price = item_price;
//        this.item_discnt = item_discnt;
//        this.item_info = item_info;
//        this.item_hit = item_hit;
//        this.item_udate = item_udate;
//        this.cate_no = cate_no;
//        this.cate_no2 = cate_no2;
//    }
}
