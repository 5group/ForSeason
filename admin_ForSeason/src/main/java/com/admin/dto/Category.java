package com.admin.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Category {
    private int cate_no;
    private String cate_name;
    private int cate_no2;
    
    private String top_cate_name;
    private String mid_cate_name;
    

	public Category(String top_cate_name, String mid_cate_name, String cate_name) {
		this.top_cate_name = top_cate_name;
		this.mid_cate_name = mid_cate_name;
		this.cate_name = cate_name;
	}
	
	public Category(String top_cate_name, String mid_cate_name) {
		this.top_cate_name=top_cate_name;
		this.mid_cate_name=mid_cate_name;
	}
	
	public Category(String top_cate_name) {
		this.top_cate_name=top_cate_name;
	}
}
