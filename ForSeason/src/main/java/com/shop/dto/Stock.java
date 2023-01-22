package com.shop.dto;

import lombok.*;

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
}
