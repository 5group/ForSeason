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


    // 장바구니 - 주문 창에서 보여질 사이즈, 색상 이름
    private String size_name;
    private String color_name;

    public Stock(int stock_no, int item_no, int color_no, int size_no, int amount) {
        this.stock_no = stock_no;
        this.item_no = item_no;
        this.color_no = color_no;
        this.size_no = size_no;
        this.amount = amount;
    }

}
