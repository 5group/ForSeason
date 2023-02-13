package com.admin.dto;

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
    
    private String cate_name;

    public Item(int cate_no, String item_name, int item_price, int item_discnt, String item_info, int item_hit, Date item_udate) {
        this.cate_no = cate_no;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_discnt = item_discnt;
        this.item_info = item_info;
        this.item_hit = item_hit;
        this.item_udate = item_udate;
    }
   
}
