package com.ssafy.happyhouse.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ssafy.happyhouse.dao.HouseInfoDAO;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.HousePageBean;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class HouseInfoServiceImpl implements HouseInfoService {

	@Autowired
	HouseInfoDAO infoDao;

	
	@Override
	public HouseInfo searchTest(String aptName, String dong) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HouseInfo> searchAllTest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTest(HouseInfo hi) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertTest(HouseInfo hi) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTest(String aptName, String dong) {
		// TODO Auto-generated method stub

	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Page<HouseInfo> searchByAptName(String AptName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HouseInfo searchById(int id) {
		// TODO Auto-generated method stub
		return infoDao.searchById(id);
	}

	@Override
	public Page<HouseInfo> searchAllBy(String orderBy, int currentPage, int sizePerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<HouseInfo> searchAll(int currentPage, int sizePerPage) {
		// TODO Auto-generated method stub
		return infoDao.searchAllHouseInfo();
	}

	@Override
	public Page<HouseInfo> searchByDong(String dong, int pageno) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageno,10);
		return infoDao.searchByDong(dong);
	}

	@Override
	public Page<HouseInfo> searchAllPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);

		return infoDao.searchAllPage();
	}
	
	@Override
	public List<String> selectGugun(String province) {
		// TODO Auto-generated method stub
		return infoDao.selectGugun(province);
	}

	@Override
	public List<String> selectDong(String city) {
		// TODO Auto-generated method stub
		return infoDao.selectDong(city);
	}

}
