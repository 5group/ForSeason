package com.admin.dto;

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

    public User(String user_id, String user_pwd, String user_email, String user_name, String user_phone, String user_address, String user_gender) {
        this.user_id = user_id;
        this.user_pwd = user_pwd;
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_address = user_address;
        this.user_gender = user_gender;
    }
}
