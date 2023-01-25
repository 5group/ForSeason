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
}
