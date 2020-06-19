package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.Range;
import com.ssafy.happyhouse.service.HouseDealService;
import com.ssafy.happyhouse.service.HouseInfoService;

import io.swagger.annotations.ApiOperation;


//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController // rest 전용
@RequestMapping("/houseinfo")
public class HouseController {

	
	@Autowired
	HouseDealService dService;

	@Autowired
	HouseInfoService iService;

	// 1. 전체 받기
	@ApiOperation(value="페이지 별로 주택 정보 반환", response = Page.class)
	@GetMapping("/all/{pageno}")
	public ResponseEntity<Map<String, Object>> selectAll(@PathVariable int pageno) {
		System.out.println("searchByDong");
		ResponseEntity<Map<String, Object>> entity = null;
		System.out.println(pageno);
		try {
			
			Page<HouseInfo> list = iService.searchAllPage(pageno,10);
			System.out.println(list);
			entity = handleSuccess(list);
			entity.getBody().put("totalPages", list.getPages());
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	//2. 주택정보 반환
	@ApiOperation(value="no로 검색",response=HouseInfo.class)
	@GetMapping("/{no}")
	public ResponseEntity<Map<String, Object>> searchById(@PathVariable int no){
		System.out.println("searchById");
		System.out.println(no);
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			HouseInfo info = iService.searchById(no);
			System.out.println(info);
			entity = handleSuccess(info);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	// 3.아파트 이름으로 찾기
	@ApiOperation(value="아파트 이름으로 검색", response = Page.class)
	@GetMapping("/apt")
	public ResponseEntity<Map<String, Object>> selectByName(@RequestParam("keyword") String name) {
		System.out.println("searchByName");
		System.out.println(name);
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			List<HouseDeal> list = (List<HouseDeal>) dService.searchByAptName(name);
			System.out.println(list);
			entity = handleSuccess(list);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}

	// 4.동으로 검색
	@ApiOperation(value="동으로 검색", response = Page.class)
	@GetMapping("/dong")
	public ResponseEntity<Map<String, Object>> selectByDong(@RequestParam("dong") String dong, @RequestParam("pageno") int pageno) {
		System.out.println("searchByDong");
		System.out.println(dong);
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			Page<HouseInfo> list = (Page<HouseInfo>) iService.searchByDong(dong,pageno);
			System.out.println(list);
			entity = handleSuccess(list);
		} catch (RuntimeException e) {
			System.out.println(e);
			entity = handleException(e);
		}
		return entity;
	}
	
	//5. 구군 검색
	@ApiOperation(value="", response = List.class)
	@GetMapping("/gugunlist")
	public ResponseEntity<Map<String, Object>> selectGugun(@RequestParam("province") String province) {
		System.out.println("selectGugun : " + province);
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			List<String> list = (List<String>) iService.selectGugun(province);
			System.out.println(list);
			entity = handleSuccess(list);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	//5. 동 검색
	@ApiOperation(value="", response = List.class)
	@GetMapping("/donglist")
	public ResponseEntity<Map<String, Object>> selectDong(@RequestParam("city") String city) {
		System.out.println("selectDong");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			List<String> list = (List<String>) iService.selectDong(city);
			System.out.println(list);
			entity = handleSuccess(list);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@ApiOperation(value="동으로 최고시세 조회", response = Page.class)
	@PostMapping("/HPbyDong")
	public ResponseEntity<Map<String, Object>> selectHighestPrice(@RequestParam("keyword") String name) {
		System.out.println("selectHighestPrice");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			HouseDeal housedeal = dService.searchHighestPrice(name);
			System.out.println(housedeal);
			entity = handleSuccess(housedeal);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@ApiOperation(value="동으로 최저시세 조회", response = Page.class)
	@PostMapping("/LPbyDong")
	public ResponseEntity<Map<String, Object>> selectLowestPrice(@RequestParam("keyword") String name) {
		System.out.println("selectHighestPrice");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			HouseDeal housedeal = dService.searchLowestPrice(name);
			System.out.println(housedeal);
			entity = handleSuccess(housedeal);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@ApiOperation(value="동으로 평균시세 조회", response = Page.class)
	@PostMapping("/AVRbyDong")
	public ResponseEntity<Map<String, Object>> selectAveragePrice(@RequestParam("keyword") String name) {
		System.out.println("selectHighestPrice");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			int average = dService.searchAveragePrice(name);
			System.out.println(average);
			entity = handleSuccess(average);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@ApiOperation(value="동으로 시세 범위 조회", response = Page.class)
	@PostMapping("/PriceRangebyDong/{pageno}")
	public ResponseEntity<Map<String, Object>> selectDongRangePrice(@PathVariable int pageno, @RequestBody Range range) {
		System.out.println("selectHighestPrice");
		System.out.println("pageno: "+pageno);
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			Page<HouseDeal> list = (Page<HouseDeal>) dService.searchDongPriceRange(pageno, range);
			System.out.println(list);
			entity = handleSuccess(list);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@ApiOperation(value="아파트이름으로 시세 범위 조회", response = Page.class)
	@PostMapping("/PriceRangebyApt/{pageno}")
	public ResponseEntity<Map<String, Object>> selectAptRangePrice(@PathVariable int pageno, @RequestBody Range range) {
		System.out.println("selectHighestPrice");
		System.out.println("pageno: "+pageno);
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			Page<HouseDeal> list = (Page<HouseDeal>) dService.searchAptPriceRange(pageno, range);
			System.out.println(list);
			entity = handleSuccess(list);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@ApiOperation(value="동으로 면적 범위 조회", response = Page.class)
	@PostMapping("/AreaRangebyDong/{pageno}")
	public ResponseEntity<Map<String, Object>> selectDongRangeArea(@PathVariable int pageno, @RequestBody Range range) {
		System.out.println("selectHighestPrice");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			List<HouseDeal> list = (List<HouseDeal>) dService.searchDongAreaRange(pageno, range);
			System.out.println(list);
			entity = handleSuccess(list);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	@ApiOperation(value="아파트이름으로 면적 범위 조회", response = Page.class)
	@PostMapping("/AreaRangebyApt/{pageno}")
	public ResponseEntity<Map<String, Object>> selectAptRangeArea(@PathVariable int pageno, @RequestBody Range range) {
		System.out.println("selectHighestPrice");
		ResponseEntity<Map<String, Object>> entity = null;
		try {
			List<HouseDeal> list = (List<HouseDeal>) dService.searchAptAreaRange(pageno, range);
			System.out.println(list);
			entity = handleSuccess(list);
		} catch (RuntimeException e) {
			entity = handleException(e);
		}
		return entity;
	}
	
	


	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", true);
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
//		logger.error("예외 발생", e);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", false);
		resultMap.put("data", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
