package com.ssafy.happyhouse.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HousePageBean;

@Mapper
public interface HouseInfoDAO {
	/**HouseInfo DB에 위도 경도 입력하기 위해서  등록된 모든 집의 동과 지번을 추출한다.  */
	public Page<HouseInfo> searchAllHouseInfo( ) ;
	/**주택정보 아파트명,동으로 조회 */
	public HouseInfo search(String aptName, String dong); 
	/**주택정보 등록*/
	public int insert(HouseInfo hi) ;
	/**주택정보 삭제*/
	public int delete(String aptName, String dong); 
	/**주택정보 수정*/
	public int update( HouseInfo hi) ;
	public Page<HouseInfo> searchAllHouseInfo( int currentPage, int sizePerPage); 
	public Page<HouseInfo> searchAllHouseInfoBy( String orderBy, int currentPage, int sizePerPage);
	public Page<HouseInfo> searchByAptName( String AptName); 
	public Page<HouseInfo> searchByDong( String dong) ;
	public HouseInfo searchById( int id); 
	public Page<HouseInfo> searchAll(HousePageBean bean, int currentPage, int sizePerPage); 
	
	public Page<HouseInfo> searchAllPage();
	public List<String> selectGugun(String province);
	public List<String> selectDong(String city);
}
