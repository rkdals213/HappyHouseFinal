package com.ssafy.happyhouse.service;

import java.util.Collection;
import java.util.List;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HousePageBean;
import com.ssafy.happyhouse.util.PageNavigation;


public interface HouseInfoService {

	/**주택정보 번호로 조회 테스트*/
	public HouseInfo searchTest(String aptName, String dong);
	/**주택정보 리스트 조회 테스트*/
	public List<HouseInfo> searchAllTest();
	/**주택정보 수정 테스트*/
	public void updateTest(HouseInfo hi);
	/**주택정보 추가 테스트*/
	public void insertTest(HouseInfo hi);
	/**주택정보 번호로 삭제 테스트*/
	public void deleteTest(String aptName, String dong);
	public Page<HouseInfo> searchByAptName(String AptName);
	public HouseInfo searchById(int id);
	public Page<HouseInfo> searchAllBy(String orderBy, int currentPage, int sizePerPage);
	public Page<HouseInfo> searchAll(int currentPage, int sizePerPage);
	public Page<HouseInfo> searchByDong(String dong, int pageno);

	
	public Page<HouseInfo> searchAllPage(int pageNo, int pageSize);
	public List<String> selectGugun(String province);
	public List<String> selectDong(String city);
		
	
}
