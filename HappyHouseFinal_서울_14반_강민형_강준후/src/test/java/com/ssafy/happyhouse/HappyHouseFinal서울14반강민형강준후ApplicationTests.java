package com.ssafy.happyhouse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.dao.BoardDAO;
import com.ssafy.happyhouse.dto.Board;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.Range;
import com.ssafy.happyhouse.service.HouseDealService;
import com.ssafy.happyhouse.service.HouseInfoService;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@SpringBootTest
class HappyHouseFinal서울14반강민형강준후ApplicationTests {

	
	@Autowired
	HouseInfoService infoService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void houseinfoTest() {
		Page<HouseInfo> infos = infoService.searchAllPage(1, 10);
		log.info(infos.toString());
		assertEquals(infos.getTotal(), 5995);
		assertEquals(infos.getPages(),600);
	}
	
	@Test 
	void houseinfoTest2() {
		HouseInfo info = infoService.searchById(4);
		assertEquals(info.getCode(), 11110);
		log.debug(info.toString());
	}
	

	@Autowired
	BoardDAO boardDao;
	
//	@Test
//	void boardsearch() {
//		Page<Board> boards = boardDao.selectBySub(3, "3번");
//		assertEquals(boards.size(), 1);
//		log.debug(boards.toString());
//	}
	
	@Test
	void gugun(){
		List<String> guguns = infoService.selectGugun("서울특별시");
		assertEquals(guguns.size(), 25);
		log.debug(guguns.size());
	}
	@Autowired
	HouseDealService dealService;
	
	@Test 
	void housedealmax() {
		System.out.println(dealService.searchHighestPrice("사직"));
		
	}
	@Test 
	void housedealmin() {
		System.out.println(dealService.searchLowestPrice("사직"));
		
	}
	@Test 
	void housedealavr() {
		System.out.println(dealService.searchAveragePrice("사직"));
		
	}
	@Test 
	void housedealrangeprice() {
		System.out.println(dealService.searchDongPriceRange(1,new Range(7, 120, "사직")));
		System.out.println(dealService.searchAptPriceRange(1,new Range(7, 120, "광화문")));
		
	}
	@Test 
	void housedealrangearea() {
		System.out.println(dealService.searchDongAreaRange(1,new Range(7, 120, "사직")));
		System.out.println(dealService.searchAptAreaRange(1,new Range(7, 120, "광화문")));
		

	}
	
	@Test
	void boardsearch() {
		System.out.println(boardDao.selectBySub(2, "취미").toString());
	}
}
