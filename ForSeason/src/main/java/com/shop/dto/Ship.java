package com.shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ship {
    private int ship_no;
    private String ship_addr;
    private String ship_cust;
    private String ship_stat;
}
