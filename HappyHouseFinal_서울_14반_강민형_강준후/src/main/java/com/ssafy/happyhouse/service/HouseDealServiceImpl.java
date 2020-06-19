package com.ssafy.happyhouse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.happyhouse.dao.HouseDealDAO;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HousePageBean;
import com.ssafy.happyhouse.dto.Range;
import com.ssafy.happyhouse.util.PageNavigation;


@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	HouseDealDAO dDao;

	@Override
	public List<HouseDeal> searchAll(HousePageBean bean, int currentPage, int sizePerPage) {
		// TODO Auto-generated method stub
		return dDao.searchAll(bean, currentPage, sizePerPage);
	}

	@Override
	public HouseDeal search(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTest(int no, HouseDeal hd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertTest(HouseDeal hd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTest(int no) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Collection<HouseDeal> searchByAptName(String AptName) {
		// TODO Auto-generated method stub
		return dDao.searchByAptName(AptName);
	}

	@Override
	public Collection<HouseDeal> searchByDong(String dong) {
		// TODO Auto-generated method stub
		return dDao.searchByDong(dong);
	}

	@Override
	public HouseDeal searchHighestPrice(String dong) {
		return dDao.searchHighestPrice(dong);
	}
	@Override
	public HouseDeal searchLowestPrice(String dong) {
		return dDao.searchLowestPrice(dong);
	}

	@Override
	public int searchAveragePrice(String dong) {
		return dDao.searchAveragePrice(dong);
	}

	@Override
	public Page<HouseDeal> searchDongPriceRange(int pageno, Range range) {
		PageHelper.startPage(pageno, 10);
		return dDao.searchDongPriceRange(range);
	}

	@Override
	public Page<HouseDeal> searchDongAreaRange(int pageno,Range range) {
		PageHelper.startPage(pageno, 10);
		return dDao.searchDongAreaRange(range);
	}

	@Override
	public Page<HouseDeal> searchAptPriceRange(int pageno, Range range) {
		PageHelper.startPage(pageno, 10);
		return dDao.searchAptAreaRange(range);
	}

	@Override
	public Page<HouseDeal> searchAptAreaRange(int pageno, Range range) {
		PageHelper.startPage(pageno, 10);
		return dDao.searchAptAreaRange(range);
	}
	
	
	

}
