package com.ssafy.happyhouse.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HousePageBean;
import com.ssafy.happyhouse.dto.Range;
import com.ssafy.happyhouse.util.PageNavigation;

public interface HouseDealService {
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<HouseDeal> searchAll(HousePageBean  bean,int currentPage, int sizePerPage);
	
	/**
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HouseDeal search(int no);
	public void updateTest(int no,HouseDeal hd);
	public void insertTest(HouseDeal hd);
	public void deleteTest(int no);

	Collection<HouseDeal> searchByAptName( String AptName); 
	Collection<HouseDeal> searchByDong( String dong) ;

	// 주택 매매 정보 조회
	// 시세검색
	// 최고시세
	HouseDeal searchHighestPrice(String dong) ;
	// 최저시세
	HouseDeal searchLowestPrice(String dong) ;
	// 평균시세 값
	int searchAveragePrice(String dong) ;

	Collection<HouseDeal> searchDongPriceRange(int pageno, Range range) ;
	Collection<HouseDeal> searchDongAreaRange(int pageno,Range range) ;
	Collection<HouseDeal> searchAptPriceRange(int pageno, Range range) ;
	Collection<HouseDeal> searchAptAreaRange(int pageno,Range range) ;
	
}
