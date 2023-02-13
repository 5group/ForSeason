package com.shop.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Qna {

	private int qna_no;
	private int user_no;
	private String qna_title;
	private String qna_content;
	private Date qna_rdate;
	
	private int rep_no;
	private String admin_id;
	private String rep_content;
	private Date rep_date;
	
	
	//insert in
	public Qna(int user_no, String qna_title, String qna_content) {
		super();
		this.user_no = user_no;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
	}
}
