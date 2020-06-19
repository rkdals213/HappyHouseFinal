package com.ssafy.happyhouse.dto;
import java.io.Serializable;
import java.util.Arrays;

public class HousePageBean implements Serializable {
	private String  city;
	private String  dong;
	private String  dongcode;
	private String  aptname;
	private String  dealtype="all";
	/** 
	 * 0 : 아파트 매매
	 * 1 : 아파트 전월세
	 * 2 : 다세대 매매
	 * 3 : 다세대 전월세  
	 * */
	private boolean[] searchType;
	private String orderType;
	private boolean isDesc;
	
	/**웹 페이지 링크*/
	private String 	pagelink;
	/**조회할 페이지 번호*/
	private int 	pageNo;
	/**한 페이지에 표시할 데이타 수*/
	private int 	interval = 5;
	/**한 페이지에 표시할 시작 번호*/
	private int 	start=1;
	/**한 페이지에 표시할 끝 번호 */
	private int 	end=interval;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getAptname() {
		return aptname;
	}
	public void setAptname(String aptname) {
		this.aptname = aptname;
	}
	
	public String getDealtype() {
		return dealtype;
	}
	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}
	public boolean[] getSearchType() {
		return searchType;
	}
	public void setSearchType(boolean[] searchType) {
		this.searchType = searchType;
	}
	public String getPagelink() {
		return pagelink;
	}
	public void setPagelink(String pagelink) {
		this.pagelink = pagelink;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public boolean isDesc() {
		return isDesc;
	}
	public void setDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}
	@Override
	public String toString() {
		return "HousePageBean [city=" + city + ", dong=" + dong + ", dongcode=" + dongcode + ", aptname=" + aptname
				+ ", dealtype=" + dealtype + ", searchType=" + Arrays.toString(searchType) + ", orderType=" + orderType
				+ ", pagelink=" + pagelink + ", pageNo=" + pageNo + ", interval=" + interval + ", start=" + start
				+ ", end=" + end + "]";
	}
	
	
}
