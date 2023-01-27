package com.admin.dto;

import lombok.*;

import java.lang.ref.PhantomReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Stock {
    private int stock_no;
    private int item_no;
    private int color_no;
    private int size_no;
    private int amount;

    public Stock(int stock_no, int item_no, int color_no, int size_no, int amount) {
        this.stock_no = stock_no;
        this.item_no = item_no;
        this.color_no = color_no;
        this.size_no = size_no;
        this.amount = amount;
    }
    private Date order_udate;
    private int cate_no;
    private int order_tot;
    private String cate_name;
    private String item_name;
    private String size_name;
    private String color_name;
    private String user_no;
    private String ship_addr;
    private String ship_cust;
}
