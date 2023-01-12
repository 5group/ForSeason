package com.shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishList {
    private int wish_no;
    private int user_no;
    private int item_no;

}
