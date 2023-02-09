package com.shop.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {

    private int rev_no;
    private int item_no;
    private int user_no;
    private String user_id; //설재경 추가
    private String rev_title;
    private String rev_content;
    private double rev_score; //설재경 추가
    private Date rev_udate;
    private Date rev_rdate;
	private int rcnt;

    
	//insert 관련
    public Review(int rev_no, int item_no, int user_no, String rev_title, String rev_content, double rev_score,
			Date rev_udate, Date rev_rdate) {
		this.rev_no = rev_no;
		this.item_no = item_no;
		this.user_no = user_no;
		this.rev_title = rev_title;
		this.rev_content = rev_content;
		this.rev_score = rev_score;
		this.rev_udate = rev_udate;
		this.rev_rdate = rev_rdate;
	}

    public Review(int user_no, int item_no, String rev_title, String rev_content, double rev_score) {
        this.item_no = item_no;
        this.user_no = user_no;
        this.rev_title = rev_title;
        this.rev_content = rev_content;
        this.rev_score = rev_score;
    }
}
