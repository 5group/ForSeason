package com.shop.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private int user_no;
    private String user_id;
    private String user_pwd;
    private String user_email;
    private String user_name;
    private String user_phone;
    private String user_address;
    private String user_gender;
    private Date user_udate;
    private Date user_rdate;

}
