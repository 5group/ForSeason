package com.shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetail {
    private int od_no;
    private int order_no;
    private int item_no;
    private int od_cnt;
    private int od_price;
}
