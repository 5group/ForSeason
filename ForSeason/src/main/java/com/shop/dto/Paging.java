package com.shop.dto;

public class Paging {
	//페이징
		private int nowPage=1;//현재페이지 (초기값=1)
		private int onePageRecord=6;//한페이지에 표시할 레코드 수 
		private int totalRecord; //총레코드 수
		private int totalPage; //총페이지 수
		private int limitStart; //mysql에서 Limit으로 페이지 불러올 때 사용되는 변수
		
		//페이지 번호 매길때 정보
		private int onePageCount=5; //한번에 표시할 페이지 수
		private int startPage=1; //시작페이지
		private int selectRecord=onePageRecord;//마지막페이지 선택할 레코드
		
		//검색, 정렬
		private String searchWord="";  //초기값은 공백
		private String orderBy="new"; //초기값은 new(신상품순으로 정렬)
		
		@Override
		public String toString() {
			return "PagingDTO [nowPage=" + nowPage + ", onePageRecord=" + onePageRecord + ", totalRecord=" + totalRecord
					+ ", totalPage=" + totalPage + ", limitStart=" + limitStart + ", onePageCount=" + onePageCount + ", startPage=" + startPage
					+ ", selectRecord=" + selectRecord + ", searchWord=" + searchWord + ", orderBy=" + orderBy + "]";
		}		
		
		
		public int getLimitStart() {
			return limitStart;
		}
		public void setLimitStart(int nowPage) {
			this.limitStart = (nowPage-1)*onePageRecord;
		}


		public int getNowPage() {
			return nowPage;
		}
		public void setNowPage(int nowPage) {
			this.nowPage = nowPage;
			
			//시작페이지 계산하기 ((현재페이지-1)/한번에 표시할 페이지수*한번에 표시할 페이지수)+1
			startPage=((nowPage-1)/onePageCount*onePageCount)+1;
		}
		public int getOnePageRecord() {
			return onePageRecord;
		}
		public void setOnePageRecord(int onePageRecord) {
			this.onePageRecord = onePageRecord;
		}
		public int getTotalRecord() {
			return totalRecord;
		}
		public void setTotalRecord(int totalRecord) {
			this.totalRecord = totalRecord;
			
			//총페이지수 구하기	    //무조건 올림    //4.1->5.0->5
			totalPage=(int)Math.ceil((double)totalRecord/onePageRecord); 
			
			selectRecord=totalRecord%onePageRecord;
			if(selectRecord==0) {
				selectRecord=onePageRecord;
			}
		}
		public int getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}

		
		//페이지번호
		public int getOnePageCount() {
			return onePageCount;
		}
		public void setOnePageCount(int onePageCount) {
			this.onePageCount = onePageCount;
		}
		public int getStartPage() {
			return startPage;
		}
		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}
		public int getSelectRecord() {
			return selectRecord;
		}
		public void setSelectRecord(int selectRecord) {
			this.selectRecord = selectRecord;
		}


		//검색, 정렬
		public String getSearchWord() {
			return searchWord;
		}

		public void setSearchWord(String searchWord) {
			this.searchWord = searchWord;
		}

		public String getOrderBy() {
			return orderBy;
		}

		public void setOrderBy(String orderBy) {
			this.orderBy = orderBy;
		}
		
}
