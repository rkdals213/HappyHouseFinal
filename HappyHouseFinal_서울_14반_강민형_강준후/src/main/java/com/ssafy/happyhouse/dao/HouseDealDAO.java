package com.ssafy.happyhouse.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HousePageBean;
import com.ssafy.happyhouse.dto.Range;

@Mapper
public interface HouseDealDAO {
	/**
	 * 아파트 거래 정보를 xml 파일에서 로딩하는 기능 
	 */
	public void loadData();
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<HouseDeal> searchAll(HousePageBean  bean,int currentPage, int sizePerPage) ;
	/**;
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HouseDeal search(int no) ;
	
	/**주택거래 정보 추가*/
	public int insert(HouseDeal hd) ;
	/**주택거래 정보 삭제*/
	public int delete(int no);
	/**주택거래 정보 수정*/
	public int update( HouseDeal before, HouseDeal after) ;
	Collection<HouseDeal> searchByAptName( String AptName); 
	Collection<HouseDeal> searchByDong( String dong) ;
	
	public Page<HouseDeal> searchAll();
	
	
	HouseDeal searchHighestPrice(String dong) ;
	HouseDeal searchLowestPrice(String dong) ;
	int searchAveragePrice(String dong) ;
	
	Page<HouseDeal> searchDongPriceRange(Range range) ;
	Page<HouseDeal> searchDongAreaRange(Range range) ;
	Page<HouseDeal> searchAptPriceRange(Range range) ;
	Page<HouseDeal> searchAptAreaRange(Range range) ;

}
