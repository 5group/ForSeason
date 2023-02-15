package com.admin.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reply {

	private int qna_no;
	private int user_no;
	private String user_id;
	private String qna_title;
	private String qna_content;
	private Date qna_rdate;
	
	private int rep_no;
	private String admin_id;
	private String rep_content;
	private Date rep_date;
	
	
	public Reply(int qna_no, String admin_id, String rep_content, Date rep_date) {
		super();
		this.qna_no = qna_no;
		this.admin_id = admin_id;
		this.rep_content = rep_content;
		this.rep_date = rep_date;
	}


	public Reply(int rep_no, String rep_content, Date rep_date) {
		super();
		this.rep_no = rep_no;
		this.rep_content = rep_content;
		this.rep_date = rep_date;
	}
	
}
